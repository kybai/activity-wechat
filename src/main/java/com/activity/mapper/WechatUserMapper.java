package com.activity.mapper;

import com.activity.model.WechatUser;

import java.util.List;

public interface WechatUserMapper {

    WechatUser selectOne(Integer id);

    WechatUser selectByOpenid(String openid);

    List<WechatUser> selectByUserId(Integer userId);

    int insert(WechatUser record);

    int update(WechatUser record);
}