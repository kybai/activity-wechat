package com.activity.service.impl;

import com.activity.mapper.WechatConfigMapper;
import com.activity.model.WechatConfig;
import com.activity.service.WechatConfigService;
import com.activity.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by ky.bai on 2018-03-04
 */
@Service
public class WechatConfigServiceImpl implements WechatConfigService {

    @Autowired
    private WechatConfigMapper wechatConfigMapper;

    @Override
    public String selectTextByKey(String configKey) {
        WechatConfig config = wechatConfigMapper.selectByKey(configKey);
        return (config == null) ? "" : config.getConfigText();
    }

    @Override
    public String getWechatRedirectUrl(String redirectUrl) {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + selectTextByKey(Constants.WECHAT_CONFIG_APPID)
                + "&redirect_uri=" + selectTextByKey(Constants.WECHAT_CONFIG_URI) + "/activity" + redirectUrl
                + "&response_type=code&scope=snsapi_userinfo&state=" + Constants.WECHAT_STATE_INDEX + "#wechat_redirect";
    }

    @Override
    public String getWechatRedirectIndexUrl() {
        return getWechatRedirectUrl("/wechat/portal/index");
    }

}
