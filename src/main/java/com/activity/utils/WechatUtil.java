package com.activity.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
        logger.info("codeMap size is：" + codeMap.keySet().size() + ", and this code is：" + code);
        if (StringUtils.isEmpty(code)) {
            return "";
        }
        //Map中含有code并且未过期时不再去请求获取token
        WechatCode wechatCode = codeMap.get(code);
        if (wechatCode != null && wechatCode.getExpireTime().getTime() + 5 * 60 * 1000 > DateUtils.getCurrentTimestamp().getTime()) {
            return wechatCode.getOpenid();
        }
        return "";
    }

    public static void setWechatCode(WechatCode wechatCode) {
        if (wechatCode != null && !StringUtils.isEmpty(wechatCode.getCode())) {
            codeMap.put(wechatCode.getCode(), wechatCode);
        }
    }

    /**
     * 将emoji表情替换成空串
     *
     * @param name 原字符
     *
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String name) {
        if (!StringUtils.isEmpty(name)) {
            return name.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
        } else {
            return name;
        }
    }
}
