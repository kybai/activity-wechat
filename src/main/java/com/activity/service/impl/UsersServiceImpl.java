package com.activity.service.impl;

import com.activity.mapper.UsersMapper;
import com.activity.model.Users;
import com.activity.pojo.BasePageList;
import com.activity.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Created by ky.bai on 2018-02-12
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users selectOne(Integer id) {
        return usersMapper.selectOne(id);
    }

    @Override
    public Users selectUserScore(Integer id) {
        return usersMapper.selectUserScore(id);
    }



    @Override
    public PageInfo<Users> findList(BasePageList page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        return new PageInfo<>(usersMapper.selectList(new Users(page.getName())));
    }

    @Override
    @Transactional
    public int insert(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    @Transactional
    public int update(Users users) {
        return usersMapper.update(users);
    }
}
