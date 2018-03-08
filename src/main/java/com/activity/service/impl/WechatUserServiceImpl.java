package com.activity.service.impl;

import com.activity.mapper.WechatUserMapper;
import com.activity.model.Users;
import com.activity.model.WechatUser;
import com.activity.service.UsersService;
import com.activity.service.WechatUserService;
import com.activity.utils.DateUtils;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author Create by ky.bai on 2018-02-11 11:15
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserMapper wechatUserMapper;

    @Autowired
    private UsersService usersService;

    @Override
    public WechatUser findByOpenid(String openid) {
        return wechatUserMapper.selectByOpenid(openid);
    }

    @Override
    @Transactional
    public int insert(WechatUser user) {
        return wechatUserMapper.insert(user);
    }

    @Override
    @Transactional
    public int insertByWxMpUser(WxMpUser mp) {
        WechatUser model = wechatUserMapper.selectByOpenid(mp.getOpenId());
        if (model != null) {
            model.setSubscribe(mp.getSubscribe());
            return wechatUserMapper.update(model);
        } else {
            Timestamp currentTime = DateUtils.getCurrentTimestamp();
            Users u = new Users(mp.getNickname(), mp.getSex(), mp.getCity(), mp.getProvince(), mp.getCountry(), mp.getHeadImgUrl(), Boolean.TRUE, currentTime);
            usersService.insert(u);
            Boolean subscribe = mp.getSubscribe() == null ? Boolean.FALSE : mp.getSubscribe();
            return wechatUserMapper.insert(new WechatUser(mp.getOpenId(), u.getId(), mp.getNickname(), subscribe, mp.getUnionId(), mp.getRemark(), mp.getGroupId(), currentTime));
        }
    }

    @Override
    @Transactional
    public int update(WechatUser entity) {
        return wechatUserMapper.update(entity);
    }
}
