package com.activity.controller;

import com.activity.service.WechatConfigService;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Create by ky.bai on 2018-02-07 19:58
 */
@RestController
@RequestMapping("/wechat/menu")
public class WxMenuController implements WxMpMenuService {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private WechatConfigService wechatConfigService;

    @GetMapping("/create")
    public String menuCreate(WxMenu wxMenu) throws WxErrorException {
        WxMenu menu = new WxMenu();

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("青年社区");

        WxMenuButton button31 = new WxMenuButton();
        button31.setType(MenuButtonType.VIEW);
        button31.setName("青年社区学校");
        button31.setUrl(wechatConfigService.getWechatRedirectUrl("/wechat/portal/index"));
        button3.getSubButtons().add(button31);

        menu.getButtons().add(button3);

        return this.wxService.getMenuService().menuCreate(menu);
    }

    @Override
    @GetMapping("/create/{json}")
    public String menuCreate(@PathVariable String json) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(json);
    }

    public void menuDelete() throws WxErrorException {

    }

    public void menuDelete(String s) throws WxErrorException {

    }

    public WxMpMenu menuGet() throws WxErrorException {
        return null;
    }

    public WxMenu menuTryMatch(String s) throws WxErrorException {
        return null;
    }

    public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
        return null;
    }
}
