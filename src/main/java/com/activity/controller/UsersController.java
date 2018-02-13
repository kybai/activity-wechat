package com.activity.controller;

import com.activity.model.Users;
import com.activity.pojo.BaseDisabled;
import com.activity.pojo.BasePageList;
import com.activity.service.UsersService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Created by ky.bai on 2018-02-12
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(Model model) {
        return "activity/users/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listPost(@RequestBody BasePageList page) {
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, usersService.findList(page)));
    }

    @RequestMapping(value = "/info/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity update(@RequestBody BaseDisabled pojo) {
        Users users = usersService.selectOne(pojo.getId());
        if (users != null) {
            users.setActive(!users.getActive());
            usersService.update(users);
        }
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }
}
