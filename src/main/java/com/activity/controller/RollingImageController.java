package com.activity.controller;

import com.activity.model.Adsense;
import com.activity.model.RollingImage;
import com.activity.pojo.BasePageList;
import com.activity.service.RollingImageService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import com.github.pagehelper.PageHelper;
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
 * @author Create by ky.bai on 2018-02-24 14:27
 */
@Controller
@RequestMapping("/rollingimg")
public class RollingImageController {

    @Autowired
    private RollingImageService rollingImageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet() {
        return "activity/rollingimg/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listPost(@RequestBody BasePageList pojo) {
        PageHelper.startPage(pojo.getCurrentPage(), pojo.getPageSize());
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, rollingImageService.selectList(new RollingImage(pojo.getName(), pojo.getActive()))));
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String info(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", rollingImageService.selectOne(id));
        return "activity/rollingimg/info";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody RollingImage record) {
        rollingImageService.insert(record);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity update(@RequestBody RollingImage record) {
        rollingImageService.update(record);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        rollingImageService.delete(id);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

}
