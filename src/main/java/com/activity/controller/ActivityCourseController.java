package com.activity.controller;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.model.ActivityCourse;
import com.activity.pojo.BasePageList;
import com.activity.service.WechatConfigService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by ky.bai on 2018-03-03
 */
@Controller
@RequestMapping("/course")
public class ActivityCourseController {

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private WechatConfigService wechatConfigService;

    /**
     * Created by ky.bai on 2018/3/3 13:28
     *
     * @param activityId 活动编号
     * @return 活动签到码
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(@RequestParam Integer activityId, Model model) {
        model.addAttribute("activityId", activityId);
        model.addAttribute("appId", wechatConfigService.selectTextByKey(Constants.WECHAT_CONFIG_APPID));
        model.addAttribute("uri", wechatConfigService.selectTextByKey(Constants.WECHAT_CONFIG_URI));
        return "activity/course/list";
    }

    /**
     * Created by ky.bai on 2018/3/3 13:39
     *
     * @param page 分页数据：otherId充当activityId
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listPost(@RequestBody BasePageList page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS,
                new PageInfo<>(activityCourseMapper.selectCodeList(new ActivityCourse(page.getOtherId())))));
    }

}
