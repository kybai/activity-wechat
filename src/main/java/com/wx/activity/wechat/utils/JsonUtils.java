package com.wx.activity.wechat.utils;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author ky.bai
 * @date 2018-02-04 18:47
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        return WxMpGsonBuilder.create().toJson(obj);
    }
}
