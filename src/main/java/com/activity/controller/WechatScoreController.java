package com.activity.controller;

import com.activity.model.Adsense;
import com.activity.model.UsersScore;
import com.activity.model.WechatUser;
import com.activity.service.AdsenseService;
import com.activity.service.UsersService;
import com.activity.service.WechatUserService;
import com.activity.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-03-02 15:18
 */
@Controller
@RequestMapping("/wechat/user")
public class WechatScoreController {

    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AdsenseService adsenseService;

    /**
     * Created by ky.bai on 2018-03-02 15:19
     *
     * @return 用户积分页面
     */
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public String getScore(@RequestParam String openid, Model model) {
        WechatUser wechatUser = wechatUserService.findByOpenid(openid);
        model.addAttribute("user", usersService.selectUserScore(wechatUser.getUserId()));
        model.addAttribute("scores", usersService.selectUserScoreList(new UsersScore(wechatUser.getUserId())));
        List<Adsense> adsenses = adsenseService.selectList(new Adsense(Constants.IMAGE_TYPE_ADSENSE, Boolean.TRUE));
        model.addAttribute("adsense", ObjectUtils.isEmpty(adsenses) ? new Adsense() : adsenses.get(0));
        return "wechat/my";
    }
}
