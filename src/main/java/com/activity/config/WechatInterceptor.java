package com.activity.config;

import com.activity.service.WechatConfigService;
import com.activity.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信端拦截器
 *
 * @author Create by ky.bai on 2018-03-06 11:06
 */
@Configuration
public class WechatInterceptor implements HandlerInterceptor {

    @Autowired
    private WechatConfigService wechatConfigService;

    /**
     * Created by ky.bai on 2018-03-06 11:15
     * 对于特殊路径，需要判断session中是否存在openid，若不存在，需要跳转到微信授权的路径
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String openid = WechatUtil.getOpenid(request);
        if (StringUtils.isEmpty(openid)) {
            response.sendRedirect(wechatConfigService.getWechatRedirectUrl());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
