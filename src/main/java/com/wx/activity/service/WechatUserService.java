package com.wx.activity.service;

import com.wx.activity.entity.WechatUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Create by ky.bai on 2018-02-11 11:15
 */
public interface WechatUserService {

    public WechatUserEntity findOne(String openid);

    public Page<WechatUserEntity> findAll(Pageable pageable);

    public void save(WechatUserEntity entity);

}
