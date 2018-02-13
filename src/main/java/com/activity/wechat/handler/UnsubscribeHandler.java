package com.activity.wechat.handler;

import com.activity.model.WechatUser;
import com.activity.service.WechatUserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 取消关注事件
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> content, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String openid = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openid);

        WechatUser model = wechatUserService.findByOpenid(openid);
        if (model != null) {
            model.setSubscribe(Boolean.FALSE);
            wechatUserService.update(model);
        }

        return null;
    }
}
