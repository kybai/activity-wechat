package com.activity.wechat.handler;

import com.activity.service.WechatConfigService;
import com.activity.utils.Constants;
import com.activity.wechat.builder.TextBuilder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户发送信息管理器
 *
 * @author Created by ky.bai on 2018-02-04
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private WechatConfigService wechatConfigService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String msg = wechatConfigService.selectTextByKey(Constants.WECHAT_CONFIG_AUTO_REPLY_MSG);
        if (!StringUtils.isEmpty(msg)) {
            return new TextBuilder().build(msg, wxMessage, wxMpService);
        }

        return null;
    }
}
