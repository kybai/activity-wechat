package com.activity.service;

import com.activity.model.WechatUser;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Create by ky.bai on 2018-02-11 11:15
 */
public interface WechatUserService {

    public WechatUser findByOpenid(String openid);

    public int insert(WechatUser entity);

    public int insertByWxMpUser(WxMpUser user);

    public int update(WechatUser entity);

}
