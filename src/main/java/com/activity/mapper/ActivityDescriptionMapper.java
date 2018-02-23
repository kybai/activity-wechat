package com.activity.mapper;

import com.activity.model.ActivityDescription;

import java.util.List;

public interface ActivityDescriptionMapper {

    int insert(ActivityDescription record);

    ActivityDescription selectByPrimaryKey(Integer activityId);

    List<ActivityDescription> selectList(ActivityDescription record);

    int updateByPrimaryKeyWithBLOBs(ActivityDescription record);

}