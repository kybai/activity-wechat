package com.wx.activity.service.impl;

import com.wx.activity.entity.WechatUserEntity;
import com.wx.activity.repository.WechatUserRepository;
import com.wx.activity.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Create by ky.bai on 2018-02-11 11:15
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserRepository wechatUserRepository;

    public WechatUserEntity findOne(String openid) {
        return wechatUserRepository.findOne(openid);
    }

    public Page<WechatUserEntity> findAll(Pageable pageable) {
        return wechatUserRepository.findAll(pageable);
    }

    @Transactional
    public void save(WechatUserEntity entity) {
        wechatUserRepository.save(entity);
    }

}
