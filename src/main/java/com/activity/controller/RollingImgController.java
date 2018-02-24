package com.activity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Created by ky.bai on 2018-02-24
 */
@Controller
@RequestMapping("/rollingimg")
public class RollingImgController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet() {
        return "activity/rollingimg/list";
    }

    //轮播图与广告位为同一表，type不同，其它相同，接口查看AdsenseController
}
