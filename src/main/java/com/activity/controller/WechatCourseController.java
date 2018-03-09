package com.activity.controller;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityCourseSignIn;
import com.activity.model.ActivityEnroll;
import com.activity.model.WechatUser;
import com.activity.pojo.WechatParamDTO;
import com.activity.service.ActivityCourseSignInService;
import com.activity.service.ActivityEnrollService;
import com.activity.service.ActivityService;
import com.activity.service.WechatUserService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
import com.activity.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private ActivityEnrollService activityEnrollService;

    /**
     * Created by ky.bai on 2018-03-02 15:34
     *
     * @param activityId 活动编号
     * @return 活动课程列表页
     */
    @RequestMapping("/info/{activityId}")
    public String list(@PathVariable Integer activityId, HttpServletRequest request, Model model) {
        String openid = WechatUtil.getOpenid(request);
        if (!StringUtils.isEmpty(openid)) {
            WechatUser wechatUser = wechatUserService.findByOpenid(openid);
            model.addAttribute("courses", activityCourseMapper.selectSignList(new WechatParamDTO(wechatUser.getUserId(), activityId, Boolean.TRUE)));
        } else {
            model.addAttribute("courses", activityCourseMapper.selectList(new ActivityCourse(activityId, Boolean.TRUE)));
        }
        model.addAttribute("activity", activityService.selectOne(activityId));
        model.addAttribute("toIndex", !Constants.WECHAT_STATE_INDEX.equals(WechatUtil.getState(request)));
        return "wechat/class";
    }

    /**
     * Created by ky.bai on 2018/3/4 11:59
     *
     * @param courseId 课程编号
     * @return 课程签到，并跳转至签到成功页面
     */
    @RequestMapping(value = "/sign/{courseId}", method = RequestMethod.GET)
    public String courseSign(@PathVariable Integer courseId, HttpServletRequest request, Model model) {
        String msg = Constants.COURSE_SIGN_SUCCESS;
        String openid = WechatUtil.getOpenid(request);
        ActivityCourse course = activityCourseMapper.selectByPrimaryKey(courseId);
        if (course == null) {
            return "redirect:/wechat/activity";
        }

        model.addAttribute("activityId", course.getActivityId());
        model.addAttribute("toIndex", !Constants.WECHAT_STATE_INDEX.equals(WechatUtil.getState(request)));
        //是否在签到时间
        long currentTime = DateUtils.getCurrentTimestamp().getTime();
        if (course.getBeginTime().getTime() >= currentTime && course.getEndTime().getTime() <= currentTime) {
            msg = Constants.COURSE_SIGN_NOT_TIME;
        } else if (!StringUtils.isEmpty(openid)) {
            WechatUser wechatUser = wechatUserService.findByOpenid(openid);
            Integer userId = wechatUser.getUserId();
            Integer activityId = course.getActivityId();
            //是否报名
            List<ActivityEnroll> enrolls = activityEnrollService.selectList(new ActivityEnroll(activityId, userId, Boolean.TRUE));
            if (ObjectUtils.isEmpty(enrolls)) {
                msg = Constants.COURSE_SIGN_NOT_ENROLL;
            }
            //是否已签到
            List<ActivityCourseSignIn> signs = signInService.selectList(new ActivityCourseSignIn(userId, courseId));
            if (ObjectUtils.isEmpty(signs)) {
                signInService.insert(new ActivityCourseSignIn(userId, courseId));
            } else {
                msg = Constants.COURSE_SIGN_WAS_SIGNED;
            }
        }
        model.addAttribute("state", Constants.WECHAT_STATE_INDEX);
        model.addAttribute("msg", msg);

        return "wechat/signUpSuccess";
    }

}
