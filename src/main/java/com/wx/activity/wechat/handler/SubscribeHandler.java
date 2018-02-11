package com.wx.activity.wechat.handler;

import com.wx.activity.entity.WechatUserEntity;
import com.wx.activity.service.WechatUserService;
import com.wx.activity.utils.Constants;
import com.wx.activity.utils.DateUtils;
import com.wx.activity.wechat.builder.TextBuilder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 关注事件处理器
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> content, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        this.logger.info("新用户关注 OPENID: " + wxMessage.getFromUser());

        //获取微信用户的基本信息
        WxMpUser user = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
        if (user != null) {
            WechatUserEntity entity = wechatUserService.findOne(user.getOpenId());
            if (entity != null) {
                entity.setSubscribe(Boolean.TRUE);
            } else {
                entity = new WechatUserEntity(user.getOpenId(), user.getNickname(), user.getSubscribe(), user.getSexId(), user.getCity(), user.getProvince(), user.getCountry(),
                        user.getHeadImgUrl(), DateUtils.getDateByLong(user.getSubscribeTime()), user.getUnionId(), user.getRemark(), user.getGroupId(), Boolean.TRUE, DateUtils.getCurrentDate());
            }
            wechatUserService.save(entity);
        }

        try {
            return new TextBuilder().build(Constants.USER_SUBSCRIBE, wxMessage, wxMpService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }
}
