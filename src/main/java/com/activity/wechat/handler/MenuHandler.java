package com.activity.wechat.handler;

import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 菜单处理器
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class MenuHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        String msg = String.format("type:%s, event:%s, key:%s", wxMessage.getMsgType(), wxMessage.getEvent(), wxMessage.getEventKey());
        if (MenuButtonType.VIEW.equals(wxMessage.getEvent())) {
            return null;
        }
        return WxMpXmlOutMessage.TEXT().content(msg)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
    }
}
