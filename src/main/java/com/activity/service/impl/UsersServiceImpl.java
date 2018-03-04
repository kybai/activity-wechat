package com.activity.service.impl;

import com.activity.mapper.UsersMapper;
import com.activity.mapper.UsersScoreMapper;
import com.activity.model.Users;
import com.activity.model.UsersScore;
import com.activity.pojo.BasePageList;
import com.activity.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-12
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersScoreMapper usersScoreMapper;

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
    public List<UsersScore> selectUserScoreList(UsersScore record) {
        return usersScoreMapper.selectList(record);
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
