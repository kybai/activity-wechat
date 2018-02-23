package com.activity.controller;

import com.activity.model.Activity;
import com.activity.model.ActivityDistrict;
import com.activity.pojo.ActivityPojo;
import com.activity.pojo.BaseDisabled;
import com.activity.pojo.BasePageList;
import com.activity.service.ActivityDistrictService;
import com.activity.service.ActivityService;
import com.activity.service.UploadFileService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(Model model) {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        return "activity/activity/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listPost(@RequestBody BasePageList page) {
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, activityService.selectPage(page)));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model) {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        return "activity/activity/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable Integer id, Model model) {
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        Activity activity = activityService.selectOne(id);
        model.addAttribute("activity", activity);
        model.addAttribute("description", activityService.selectDesc(activity.getId()));
        model.addAttribute("file", uploadFileService.selectOne(activity.getUploadFileId()));
        return "activity/activity/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody ActivityPojo pojo) {
        activityService.save(pojo);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String getInfo(@PathVariable Integer id, Model model) {
        Activity activity = activityService.selectOne(id);
        model.addAttribute("activity", activity);
        model.addAttribute("district", activityDistrictService.selectOne(activity.getDistrictId()));
        model.addAttribute("description", activityService.selectDesc(activity.getId()));
        model.addAttribute("file", uploadFileService.selectOne(activity.getUploadFileId()));
        return "activity/activity/info";
    }

    @RequestMapping(value = "/info/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity disabled(@RequestBody BaseDisabled pojo) {
        Activity activity = activityService.selectOne(pojo.getId());
        if (activity != null) {
            activity.setActive(!activity.getActive());
            activityService.update(activity);
        }
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }
}
