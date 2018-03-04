package com.activity.mapper;

import com.activity.model.WechatConfig;

/**
 * @author Created by ky.bai on 2018-03-04
 */
public interface WechatConfigMapper {

    public WechatConfig selectByKey(String configKey);
}
