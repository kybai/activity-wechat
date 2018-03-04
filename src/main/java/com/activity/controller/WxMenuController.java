package com.activity.controller;

import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${wechat.mp.appId}")
    private String appId;

    @Value("${wechat.uri}")
    private String href;

    @GetMapping("/create")
    public String menuCreate(WxMenu wxMenu) throws WxErrorException {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(MenuButtonType.VIEW);
        button1.setName("我的门店");
        button1.setUrl("http://test.51meiy.com/shiyu_clients/storeHome/weixinAuthorization_html");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(MenuButtonType.VIEW);
        button2.setName("活动");
        button2.setUrl("http://wx.51meiy.com/app/index.php?i=2c=entrym=feng_fightgroupsdo=homeac=indexfrom=groupmessageisappinstalled=0");

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("会员服务");
        WxMenuButton button31 = new WxMenuButton();
        button31.setType(MenuButtonType.VIEW);
        button31.setName("个人中心");
        button31.setUrl("http://test.51meiy.com/shiyu_clients/storeHome/weixinAuthorization_html?pageType=personalCenter");
        WxMenuButton button32 = new WxMenuButton();
        button32.setType(MenuButtonType.CLICK);
        button32.setName("在线咨询");
        button32.setKey("HYFW_ZXZX");
        WxMenuButton button33 = new WxMenuButton();
        button33.setType(MenuButtonType.CLICK);
        button33.setName("常见问题");
        button33.setKey("HYFW_CJWT");
        button3.getSubButtons().add(button31);
        button3.getSubButtons().add(button32);
        button3.getSubButtons().add(button33);

        //自己用的路径
        WxMenuButton button34 = new WxMenuButton();
        button34.setType(MenuButtonType.VIEW);
        button34.setName("团建");
        button34.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                + "&redirect_uri=" + href + "/activity/wechat/portal/index"
                + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
        button3.getSubButtons().add(button34);

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
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
