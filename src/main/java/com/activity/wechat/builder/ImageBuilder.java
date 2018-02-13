package com.activity.wechat.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 图文消息构造器
 *
 * @author Created by ky.bai on 2018-02-04
 */
public class ImageBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {

        return WxMpXmlOutMessage.IMAGE().mediaId(content)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
    }
}
