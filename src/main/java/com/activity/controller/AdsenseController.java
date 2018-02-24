package com.activity.controller;

import com.activity.model.Adsense;
import com.activity.pojo.BasePageList;
import com.activity.service.AdsenseService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Create by ky.bai on 2018-02-24 14:27
 */
@Controller
@RequestMapping("/adsense")
public class AdsenseController {

    @Autowired
    private AdsenseService adsenseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet() {
        return "activity/adsense/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listPost(@RequestBody BasePageList pojo) {
        PageHelper.startPage(pojo.getCurrentPage(), pojo.getPageSize());
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, new PageInfo<>(adsenseService.selectList(new Adsense(pojo.getName())))));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody Adsense record) {
        if (record != null && record.getId() != null) {
            Adsense adsense = adsenseService.selectOne(record.getId());
            adsense.setUploadFileId(record.getUploadFileId());
            adsense.setUrl(record.getUrl());
            adsenseService.update(adsense);
        } else if (record != null) {
            adsenseService.insert(record);
        }
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity disabled(@PathVariable Integer id) {
        Adsense adsense = adsenseService.selectOne(id);
        adsense.setActive(!adsense.getActive());
        adsenseService.update(adsense);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

}
