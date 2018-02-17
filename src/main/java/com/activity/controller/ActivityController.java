package com.activity.controller;

import com.activity.model.Activity;
import com.activity.model.ActivityDistrict;
import com.activity.service.ActivityDistrictService;
import com.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Created by ky.bai on 2018-02-17
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityDistrictService activityDistrictService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(Model model) {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        return "activity/activity/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model) {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        return "activity/activity/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable Integer id, Model model) {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        return "activity/activity/add";
    }
}
