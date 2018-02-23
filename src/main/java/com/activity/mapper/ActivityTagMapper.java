package com.activity.mapper;

import com.activity.model.ActivityTag;

import java.util.List;

public interface ActivityTagMapper {

    int insert(ActivityTag record);

    ActivityTag selectByPrimaryKey(Integer id);

    List<ActivityTag> selectList(ActivityTag record);

    int updateByPrimaryKey(ActivityTag record);
}