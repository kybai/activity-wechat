package com.activity.controller;

import com.activity.model.ActivityDistrict;
import com.activity.pojo.BaseDisabled;
import com.activity.pojo.BasePageList;
import com.activity.service.ActivityDistrictService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
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
 * @author Created by ky.bai on 2018-02-14
 */
@Controller
@RequestMapping("/district")
public class ActivityDistrictController {

    @Autowired
    private ActivityDistrictService activityDistrictService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet() {
        return "activity/district/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listPost(@RequestBody BasePageList page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, new PageInfo<>(activityDistrictService.selectList(new ActivityDistrict(page.getName())))));
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody ActivityDistrict entity) {
        entity.setCreateDate(DateUtils.getCurrentTimestamp());
        entity.setActive(Boolean.TRUE);
        activityDistrictService.insert(entity);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@RequestBody ActivityDistrict entity) {
        activityDistrictService.update(entity);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/info/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity disabled(@RequestBody BaseDisabled base) {
        ActivityDistrict record = activityDistrictService.selectOne(base.getId());
        if (record != null) {
            record.setActive(!record.getActive());
            activityDistrictService.update(record);
        }
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

}
