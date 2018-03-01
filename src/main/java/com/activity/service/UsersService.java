package com.activity.service;

import com.activity.model.Users;
import com.activity.pojo.BasePageList;
import com.github.pagehelper.PageInfo;

/**
 * @author Created by ky.bai on 2018-02-12
 */
public interface UsersService {

    public Users selectOne(Integer id);

    public Users selectUserScore(Integer id);

    public PageInfo<Users> findList(BasePageList page);

    public int insert(Users users);

    public int update(Users users);
}
