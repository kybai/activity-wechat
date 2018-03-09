package com.activity.config;

import com.activity.model.WechatUser;
import com.activity.service.WechatConfigService;
import com.activity.service.WechatUserService;
import com.activity.utils.DateUtils;
import com.activity.utils.WechatCode;
import com.activity.utils.WechatUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 微信端拦截器
 *
 * @author Create by ky.bai on 2018-03-06 11:06
 */
@Configuration
public class WechatInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(WechatInterceptor.class);

    @Autowired
    private WechatConfigService wechatConfigService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatUserService wechatUserService;

    //授权信息：key为code
    private static ConcurrentMap<String, WechatCode> codeMap = new ConcurrentHashMap<>();

    /**
     * Created by ky.bai on 2018-03-06 11:15
     * 对于特殊路径，需要判断session中是否存在openid，若不存在，需要跳转到微信授权的路径
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws WxErrorException, IOException {
        String openidSession = WechatUtil.getOpenid(request);
        if (!StringUtils.isEmpty(openidSession)) return true;

        String openid = request.getParameter("openid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (StringUtils.isEmpty(openidSession) && StringUtils.isEmpty(openid) && StringUtils.isEmpty(code)) {
            response.sendRedirect(wechatConfigService.getWechatRedirectUrl(request.getServletPath()));
            return false;
        }

        Timestamp currentTime = DateUtils.getCurrentTimestamp();
        logger.info("codeMap size is" + codeMap.keySet().size());
        WechatCode wechatCode = codeMap.get(code);
        //Map中含有code并且未过期时不再去请求获取token
        if (wechatCode != null && wechatCode.getExpireTime().getTime() + 5 * 60 * 1000 < currentTime.getTime()) {
            openid = wechatCode.getOpenid();
        }
        if (StringUtils.isEmpty(openid) && !StringUtils.isEmpty(code)) {
            WxMpOAuth2AccessToken auth = wxMpService.oauth2getAccessToken(code);
            openid = auth.getOpenId();
            //获取微信用户的基本信息, 若微信用户还未存在，则保存
            WechatUser wechatUser = wechatUserService.findByOpenid(openid);
            WxMpUser u = wxMpService.oauth2getUserInfo(auth, null);
            if (wechatUser == null && u != null && !StringUtils.isEmpty(u.getNickname())) {
                wechatUserService.insertByWxMpUser(u);
            }
            //放入缓存中
            codeMap.put(code, new WechatCode(openid, code, auth.getAccessToken(), currentTime));
            if (StringUtils.isEmpty(openid)) openid = openidSession;
        }

        //将openid放入缓存中
        if (!StringUtils.isEmpty(openid)) WechatUtil.setOpenid(request, openid);

        request.setAttribute("state", state);//将请求转发至的参数再次放入参数中
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
