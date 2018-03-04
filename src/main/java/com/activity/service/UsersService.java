package com.activity.service;

import com.activity.model.Users;
import com.activity.model.UsersScore;
import com.activity.pojo.BasePageList;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-12
 */
public interface UsersService {

    Users selectOne(Integer id);

    //获取用户信息与用户总积分
    Users selectUserScore(Integer id);

    PageInfo<Users> findList(BasePageList page);

    //获取用户积分列表
    List<UsersScore> selectUserScoreList(UsersScore record);

    int insert(Users users);

    int update(Users users);
}
