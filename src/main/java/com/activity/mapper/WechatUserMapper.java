package com.activity.mapper;

import com.activity.model.WechatUser;

import java.util.List;

public interface WechatUserMapper {

    WechatUser selectOne(Integer id);

    WechatUser selectByOpenID(String openid);

    List<WechatUser> selectByUserID(Integer userID);

    int insert(WechatUser record);

    int update(WechatUser record);
}