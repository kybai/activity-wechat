package com.activity.controller;

import com.activity.model.ActivityEnroll;
import com.activity.pojo.BasePageList;
import com.activity.service.ActivityEnrollService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Controller
@RequestMapping("/enroll")
public class ActivityEnrollController {

    @Autowired
    private ActivityEnrollService activityEnrollService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listHtml() {
        return "activity/enroll/list";
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity list(@RequestBody BasePageList page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        //otherId充当活动编号
        PageInfo<ActivityEnroll> info = new PageInfo<>(activityEnrollService.selectList(new ActivityEnroll(page.getOtherId())));
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, info));
    }


}
