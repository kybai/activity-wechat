package com.activity.controller;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityCourseSignIn;
import com.activity.model.WechatUser;
import com.activity.pojo.WechatParamDTO;
import com.activity.service.ActivityCourseSignInService;
import com.activity.service.ActivityService;
import com.activity.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ActivityCourseSignInService signInService;

    /**
     * Created by ky.bai on 2018-03-02 15:34
     *
     * @param activityId 活动编号
     * @param openid     微信用户openid
     * @return 活动课程列表页
     */
    @RequestMapping("/info/{activityId}")
    public String list(@PathVariable Integer activityId, @RequestParam String openid, Model model) {
        if (!StringUtils.isEmpty(openid)) {
            WechatUser user = wechatUserService.findByOpenid(openid);
            model.addAttribute("courses", activityCourseMapper.selectSignList(new WechatParamDTO(activityId, user.getId(), Boolean.TRUE)));
        } else {
            model.addAttribute("courses", activityCourseMapper.selectList(new ActivityCourse(activityId, Boolean.TRUE)));
        }
        model.addAttribute("activity", activityService.selectOne(activityId));
        return "wechat/class";
    }

    /**
     * Created by ky.bai on 2018/3/4 11:59
     *
     * @param courseId 课程编号
     * @param openid   微信用户openid
     * @return 课程签到成功页面
     */
    @RequestMapping(value = "/sign/{courseId}", method = RequestMethod.GET)
    @ResponseBody
    public String courseSign(@PathVariable Integer courseId, @RequestParam String openid, Model model) {
        ActivityCourse course = activityCourseMapper.selectByPrimaryKey(courseId);
        WechatUser wechatUser = wechatUserService.findByOpenid(openid);
        signInService.insert(new ActivityCourseSignIn(wechatUser.getUserId(), course.getActivityId()));

        model.addAttribute("activityId", course.getActivityId());
        model.addAttribute("openid", openid);
        return "wechat/signUpSuccess";
    }

}
