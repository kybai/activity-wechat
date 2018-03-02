package com.activity.service;

import com.activity.model.Users;
import com.activity.pojo.BasePageList;
import com.github.pagehelper.PageInfo;

/**
 * @author Created by ky.bai on 2018-02-12
 */
public interface UsersService {

    Users selectOne(Integer id);

    //获取用户信息与用户总积分
    Users selectUserScore(Integer id);

    PageInfo<Users> findList(BasePageList page);

    int insert(Users users);

    int update(Users users);
}
