package com.activity.wechat.handler;

import com.activity.service.WechatConfigService;
import com.activity.service.WechatUserService;
import com.activity.utils.Constants;
import com.activity.wechat.builder.TextBuilder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private WechatConfigService wechatConfigService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> content, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        this.logger.info("新用户关注 OPENID: " + wxMessage.getFromUser());

        //获取微信用户的基本信息
        WxMpUser u = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
        if (u != null) {
            wechatUserService.insertByWxMpUser(u);
        }

        String msg = wechatConfigService.selectTextByKey(Constants.WECHAT_CONFIG_SUBSCRIBE_MSG);
        if (!StringUtils.isEmpty(msg)) {
            return new TextBuilder().build(msg, wxMessage, wxMpService);
        }
        return null;
    }
}
