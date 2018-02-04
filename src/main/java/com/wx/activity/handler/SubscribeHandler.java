package com.wx.activity.handler;

import com.wx.activity.builder.TextBuilder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 关注事件处理器
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> content, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        this.logger.info("新用户关注 OPENID: " + wxMessage.getFromUser());

        //获取微信用户的基本信息
        WxMpUser user = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
        //TODO 添加关注用户到本地数据库
        if (user != null) {

        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, wxMpService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }
}
