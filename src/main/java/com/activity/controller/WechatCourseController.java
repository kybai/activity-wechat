package com.activity.controller;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.model.WechatUser;
import com.activity.pojo.WechatParamDTO;
import com.activity.service.ActivityService;
import com.activity.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Create by ky.bai on 2018-03-01 16:52
 */
@Controller
@RequestMapping("/wechat/course")
public class WechatCourseController {

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private ActivityService activityService;

    /**
     * Created by ky.bai on 2018-03-02 15:34
     *
     * @param activityId 活动编号
     * @param openid     微信用户openid
     *
     * @return 活动课程列表页
     */
    @RequestMapping("/info/{activityId}")
    public String list(@PathVariable Integer activityId, @RequestParam String openid, Model model) {
        WechatUser user = wechatUserService.findByOpenid(openid);
        model.addAttribute("courses", activityCourseMapper.selectSignList(new WechatParamDTO(activityId, user.getId(), Boolean.TRUE)));
        model.addAttribute("activity", activityService.selectOne(activityId));
        return "wechat/class";
    }

    /**
     * Created by ky.bai on 2018-03-02 15:32
     *
     * @return 课程签到成功页面
     */
    @RequestMapping(value = "/sign/{activityId}", method = RequestMethod.GET)
    public String courseSign(@PathVariable Integer activityId, @RequestParam(required = false) String openid, Model model) {
        model.addAttribute("activityId", activityId);
        model.addAttribute("openid", openid);
        return "wechat/signUpSuccess";
    }

}
