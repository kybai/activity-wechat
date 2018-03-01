package com.activity.controller;

import com.activity.model.ActivityDistrict;
import com.activity.model.WechatUser;
import com.activity.pojo.WechatPojo;
import com.activity.service.ActivityDistrictService;
import com.activity.service.ActivityService;
import com.activity.service.UsersService;
import com.activity.service.WechatUserService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Create by ky.bai on 2018-03-01 8:54
 */
@Controller
@RequestMapping("/wechat/activity")
public class WechatActivityController {

    @Autowired
    private ActivityDistrictService activityDistrictService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private WxMpService wxMpService;

    /**
     * Created by ky.bai on 2018-03-01 14:49
     *
     * @param code 微信VIEW按钮对应code, 微信oauth2回调时生成并传递(须配置微信授权页面)
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(@RequestParam String code, Model model) throws WxErrorException {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        String openid = "47027414324223230";//auth.getOpenId()
        //TODO
        //WxMpOAuth2AccessToken auth = wxMpService.oauth2getAccessToken(code);
        //model.addAttribute("openid", auth.getOpenId());
        model.addAttribute("openid", openid);
        //未结束活动的封面图
        model.addAttribute("fileList", activityService.selectWechatList(new WechatPojo(Boolean.TRUE)));
        model.addAttribute("mylist", activityService.selectUserWechatList(new WechatPojo(openid, Boolean.TRUE)));
        //我的积分
        WechatUser wechatUser = wechatUserService.findByOpenid(openid);
        model.addAttribute("user", usersService.selectUserScore(wechatUser.getUserId()));
        return "wechat/list";
    }

    /**
     * Created by ky.bai on 2018-03-01 14:48
     *
     * @param id     活动编号
     * @param openid 用户微信openid
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String getInfo(@PathVariable Integer id, @RequestParam String openid, Model model) {
        model.addAttribute("openid", openid);
        model.addAttribute("activity", activityService.selectOne(id));
        return "wechat/detail";
    }

    /**
     * Created by ky.bai on 2018-03-01 11:30
     *
     * @param pojo 微信端请求参数
     *
     * @return 微信端活动列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getActivityList(@RequestBody WechatPojo pojo) {
        Map<String, Object> results = new HashMap<>();
        results.put("wechatList", activityService.selectWechatList(pojo));
        results.put("reviewList", activityService.selectWechatReviewList(pojo));
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, results));
    }

}
