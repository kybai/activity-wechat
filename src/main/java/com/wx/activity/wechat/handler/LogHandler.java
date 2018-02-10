package com.wx.activity.wechat.handler;

import com.wx.activity.wechat.utils.JsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 接收到消息的日志处理器
 *
 * @author ky.bai
 */
@Component
public class LogHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        this.logger.info("\n接收到请求消息，内容：{}", JsonUtils.toJson(wxMessage));
        return null;
    }
}
