package com.activity.mapper;

import com.activity.model.ActivityTag;

public interface ActivityTagMapper {

    int insert(ActivityTag record);

    ActivityTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ActivityTag record);
}