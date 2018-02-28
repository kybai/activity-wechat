package com.activity.service;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Create by ky.bai on 2018-02-28 10:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatUserServiceTest {

    @Autowired
    private WechatUserService wechatUserService;

    @Test
    public void insertByWxMpUserTest() {
        WxMpUser mp = new WxMpUser();
        Integer openid = 1432422323;
        mp.setOpenId("470274" + openid);
        mp.setSubscribe(Boolean.TRUE);
        mp.setNickname("小五");
        mp.setSex("男");
        mp.setCity("商丘");
        mp.setProvince("河南");
        mp.setCountry("中国");
        mp.setHeadImgUrl("http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg");
        mp.setRemark("这世间唯梦想与好姑娘不可辜负");
        int num = wechatUserService.insertByWxMpUser(mp);
    }
}
