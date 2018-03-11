package com.activity.service;

/**
 * @author Created by ky.bai on 2018-03-04
 */
public interface WechatConfigService {

    String selectTextByKey(String configKey);

    //提示用户需要授权，跳转到业务页面
    String getWechatRedirectUrl(String redirectUrl);

    String getWechatRedirectIndexUrl();
}
