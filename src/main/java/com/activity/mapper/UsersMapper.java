package com.activity.mapper;

import com.activity.model.Users;

import java.util.List;

public interface UsersMapper {

    Users selectOne(Integer id);

    List<Users> selectList(Users users);

    int insert(Users record);

    int update(Users record);
}