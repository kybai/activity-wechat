package com.activity.utils;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Create by ky.bai on 2018-03-05 16:51
 */
public class WechatUtil {

    public static String getOpenid(HttpServletRequest request) {
        Object openid = request.getSession().getAttribute("openid");
        return ObjectUtils.isEmpty(openid) ? "" : openid.toString();
    }

    public static void setOpenid(HttpServletRequest request, String openid) {
        request.getSession().setAttribute("openid", openid);
    }

    public static String getState(HttpServletRequest request) {
        return request.getParameter("state");
    }
}
