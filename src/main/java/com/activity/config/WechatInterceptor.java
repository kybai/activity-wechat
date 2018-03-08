package com.activity.config;

import com.activity.model.WechatUser;
import com.activity.service.WechatConfigService;
import com.activity.service.WechatUserService;
import com.activity.utils.DateUtils;
import com.activity.utils.WechatCode;
import com.activity.utils.WechatUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private WechatConfigService wechatConfigService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatUserService wechatUserService;

    //授权信息：key为code
    private ConcurrentMap<String, WechatCode> codeMap = new ConcurrentHashMap<>();

    /**
     * Created by ky.bai on 2018-03-06 11:15
     * 对于特殊路径，需要判断session中是否存在openid，若不存在，需要跳转到微信授权的路径
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String openidSession = WechatUtil.getOpenid(request);
        String openid = request.getParameter("openid");
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(openidSession) && StringUtils.isEmpty(openid) && StringUtils.isEmpty(code)) {
            response.sendRedirect(wechatConfigService.getWechatRedirectUrl(request.getServletPath()));
            return false;
        }

        Timestamp currentTime = DateUtils.getCurrentTimestamp();
        WechatCode wechatCode = codeMap.get(code);
        //Map中含有code并且未过期时不再去请求获取token
        if (wechatCode != null && !StringUtils.isEmpty(wechatCode.getOpenid()) && wechatCode.getExpireTime().getTime() + 5 * 60 * 1000 < currentTime.getTime()) {
            openid = wechatCode.getOpenid();
        } else if (StringUtils.isEmpty(openid) && !StringUtils.isEmpty(code)) {
            WxMpOAuth2AccessToken auth = wxMpService.oauth2getAccessToken(code);
            openid = auth.getOpenId();
            //获取微信用户的基本信息, 若微信用户还未存在，则保存
            WechatUser wechatUser = wechatUserService.findByOpenid(openid);
            WxMpUser u = wxMpService.getUserService().userInfo(auth.getOpenId(), null);
            if (wechatUser == null && u != null) {
                wechatUserService.insertByWxMpUser(u);
            }
            codeMap.put(code, new WechatCode(openid, code, auth.getAccessToken(), currentTime));
        }
        if (StringUtils.isEmpty(openid)) openid = openidSession;
        WechatUtil.setOpenid(request, openid);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
