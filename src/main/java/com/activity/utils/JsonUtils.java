package com.activity.utils;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 微信Json工具类
 * Created by ky.bai on 2018-02-28 14:29
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        return WxMpGsonBuilder.create().toJson(obj);
    }
}
