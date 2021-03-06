package com.activity.controller;

import com.activity.model.WechatUser;
import com.activity.service.WechatUserService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
import com.activity.utils.WechatCode;
import com.activity.utils.WechatUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * @author Created by ky.bai on 2018-02-04
 */
@Controller
@RequestMapping("/wechat/portal")
public class WechatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter router;

    @Autowired
    private WechatUserService wechatUserService;

    @RequestMapping(produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) throws IllegalAccessException {

        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalAccessException("请求参数非法，请核实！");
        }

        if (this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @RequestMapping(produces = "application/xml; charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String authPost(@RequestBody String body, @RequestParam(name = "signature") String signature,
                           @RequestParam(name = "timestamp") String timestamp, @RequestParam(name = "nonce") String nonce,
                           @RequestParam(name = "encrypt_type", required = false) String encryptType,
                           @RequestParam(name = "msg_signature", required = false) String msgSignature) throws IllegalAccessException {

        this.logger.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encryptType, msgSignature, timestamp, nonce, body);

        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalAccessException("非法请求，可能属于伪造请求！");
        }

        String out = null;
        if (encryptType == null) {
            //明文传输的消息
            WxMpXmlMessage inMsg = WxMpXmlMessage.fromXml(body);
            WxMpXmlOutMessage outMessage = this.route(inMsg);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toXml();
        } else if ("aes".equals(encryptType)) {
            // aes加密的消息
            WxMpXmlMessage inMsg = WxMpXmlMessage.fromEncryptedXml(body, this.wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            this.logger.debug("\n消息解密后内容为：\n{} ", inMsg.toString());
            WxMpXmlOutMessage outMessage = this.route(inMsg);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
        }
        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }

    /**
     * Created by ky.bai on 2018/3/4 09:37
     *
     * @param request weixin oauth2回调返回的code, weixin用户openid
     * @return 请求转发至活动页(微信入口或授权)
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws WxErrorException {
        String code = request.getParameter("code");
        String openid = request.getParameter("openid");
        if (StringUtils.isEmpty(openid)) {
            openid = WechatUtil.getOpenidByCode(code);
        }
        if (StringUtils.isEmpty(openid) && !StringUtils.isEmpty(code)) {
            Timestamp currentTime = DateUtils.getCurrentTimestamp();
            WxMpOAuth2AccessToken auth = wxMpService.oauth2getAccessToken(code);
            openid = auth.getOpenId();
            //获取微信用户的基本信息, 若微信用户还未存在，则保存
            WechatUser wechatUser = wechatUserService.findByOpenid(openid);
            WxMpUser u = wxMpService.oauth2getUserInfo(auth, null);
            if (wechatUser == null && u != null && !StringUtils.isEmpty(u.getNickname())) {
                wechatUserService.insertByWxMpUser(u);
            }
            //放入缓存Map中
            WechatUtil.setWechatCode(new WechatCode(openid, code, auth.getAccessToken(), currentTime));
        }
        if (!StringUtils.isEmpty(openid) && StringUtils.isEmpty(WechatUtil.getOpenid(request))) {
            WechatUtil.setOpenid(request, openid);
        }
        //是否为我的
        if (Constants.WECHAT_STATE_INDEX_MY.equals(request.getParameter("state"))) {
            return "redirect:/wechat/activity?my="+Constants.WECHAT_STATE_INDEX_MY;
        } else {
            return "redirect:/wechat/activity";
        }
    }

    /**
     * Created by ky.bai on 2018/3/4 12:04
     *
     * @param courseId 课程编号
     * @return 扫码后请求转发至课程签到接口
     */
    @RequestMapping(value = "/sign/{courseId}", method = RequestMethod.GET)
    public String signCourse(@PathVariable Integer courseId, HttpServletRequest request) throws WxErrorException {
        String code = request.getParameter("code");//weixin oauth2回调返回的code
        if (StringUtils.isEmpty(code)) {
            return "redirect:/wechat/activity";//用户不存在转发至活动首页
        }
        this.logger.info("用户签到code：" + code);
        String openid = WechatUtil.getOpenidByCode(code);
        if (StringUtils.isEmpty(openid)) {
            Timestamp currentTime = DateUtils.getCurrentTimestamp();
            WxMpOAuth2AccessToken auth = wxMpService.oauth2getAccessToken(code);
            openid = auth.getOpenId();
            //放入缓存Map中
            WechatUtil.setWechatCode(new WechatCode(openid, code, auth.getAccessToken(), currentTime));
        }
        if (!StringUtils.isEmpty(openid)) {
            WechatUser wechatUser = wechatUserService.findByOpenid(openid);
            if (wechatUser != null) {
                WechatUtil.setOpenid(request, openid);
                return "redirect:/wechat/course/sign/" + courseId;//用户存在转发至签到接口
            }
        }

        return "redirect:/wechat/activity";//用户不存在转发至活动首页
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.router.route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }
}
