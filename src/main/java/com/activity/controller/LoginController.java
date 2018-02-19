package com.activity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ky.bai on 2018/2/18 22:17
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"", "/", "/index", "/home"}, method = RequestMethod.GET)
    public String index() {
        return "activity/activity/list";
    }
}
