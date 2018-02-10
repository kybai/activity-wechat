package com.wx.activity.wechat.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 取消关注事件
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> content, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("取消关注用户 OPENID: " + wxMessage.getFromUser());
        //TODO: 可修改数据库为取消关注状态
        return null;
    }
}
