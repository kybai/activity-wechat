package com.activity.controller;

import com.activity.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ky.bai on 2018/2/18 22:17
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) model.addAttribute("error", Constants.LOGIN_FAILURE_ERROR);
        return "login";
    }

    @RequestMapping(value = {"", "/", "/index", "/home"}, method = RequestMethod.GET)
    public String index() {
        return "activity/activity/list";
    }
}
