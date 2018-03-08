package com.activity.utils;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 微信请求授权code报40163错误
 *
 * @author Create by ky.bai on 2018-03-08 9:34
 */
public class WechatCode implements Serializable {
    private static final long serialVersionUID = -3957076392858092378L;

    //微信用户openid
    private String openid;
    //授权获取到的code
    private String code;
    //根据code获取到的token
    private String token;
    //code的获取时间(判断是否过期须延长该时间5分钟)
    private Timestamp expireTime;

    public WechatCode() {
    }

    public WechatCode(String openid, String code, String token, Timestamp expireTime) {
        this.openid = openid;
        this.code = code;
        this.token = token;
        this.expireTime = expireTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }
}
