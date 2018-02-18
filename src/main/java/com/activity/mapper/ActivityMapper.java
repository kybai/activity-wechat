package com.activity.mapper;

import com.activity.model.Activity;

import java.util.List;

public interface ActivityMapper {

    int insert(Activity record);

    int update(Activity record);

    Activity selectOne(Integer id);

    List<Activity> selectList(Activity record);
}