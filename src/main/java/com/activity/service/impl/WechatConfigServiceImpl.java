package com.activity.service.impl;

import com.activity.mapper.WechatConfigMapper;
import com.activity.model.WechatConfig;
import com.activity.service.WechatConfigService;
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
}
