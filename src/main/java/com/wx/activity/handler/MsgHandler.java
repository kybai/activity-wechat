package com.wx.activity.handler;

import com.wx.activity.builder.TextBuilder;
import com.wx.activity.utils.JsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户发送信息管理器
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class MsgHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        //TODO 组装回复消息
        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);
        return new TextBuilder().build(content, wxMessage, wxMpService);
    }
}
