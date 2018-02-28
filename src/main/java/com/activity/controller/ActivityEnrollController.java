package com.activity.controller;

import com.activity.model.ActivityEnroll;
import com.activity.model.ActivityTag;
import com.activity.pojo.BasePageList;
import com.activity.service.ActivityEnrollService;
import com.activity.service.ActivityService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Controller
@RequestMapping("/enroll")
public class ActivityEnrollController {

    @Autowired
    private ActivityEnrollService activityEnrollService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listHtml(@RequestParam Integer activityId, Model model) {
        model.addAttribute("activity", activityService.selectOne(activityId));
        model.addAttribute("tag", activityService.selectTag(new ActivityTag(activityId)));
        return "activity/enroll/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity list(@RequestBody BasePageList page) {
        //otherId充当活动编号
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        PageInfo<ActivityEnroll> info = new PageInfo<>(activityEnrollService.selectList(new ActivityEnroll(page.getOtherId(), Boolean.TRUE, page.getName())));
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, info));
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getInfo(@PathVariable Integer id) {
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, activityEnrollService.selectById(id)));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody ActivityEnroll record) {
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, activityEnrollService.insert(record)));
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateStatus(@PathVariable Integer id) {
        ActivityEnroll record = activityEnrollService.selectById(id);
        if (record != null) {
            record.setActive(!record.getActive());
            activityEnrollService.update(record);
        }
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

}
