package com.activity.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Create by ky.bai on 2018-03-05 16:51
 */
public class WechatUtil {

    private static final Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    //授权信息：key为code
    private static ConcurrentMap<String, WechatCode> codeMap = new ConcurrentHashMap<>();

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

    public static String getOpenidByCode(String code) {
        Timestamp currentTime = DateUtils.getCurrentTimestamp();
        logger.info("codeMap size is：" + codeMap.keySet().size());
        //Map中含有code并且未过期时不再去请求获取token
        WechatCode wechatCode = codeMap.get(code);
        if (wechatCode != null && wechatCode.getExpireTime().getTime() + 5 * 60 * 1000 < currentTime.getTime()) {
            return wechatCode.getOpenid();
        }
        return "";
    }

    public static void setWechatCode(WechatCode wechatCode) {
        codeMap.put(wechatCode.getCode(), wechatCode);
    }
}
