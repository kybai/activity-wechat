package com.activity.mapper;

import com.activity.model.ActivityDescription;

public interface ActivityDescriptionMapper {

    int insert(ActivityDescription record);

    ActivityDescription selectByPrimaryKey(Integer id);

    int updateByPrimaryKeyWithBLOBs(ActivityDescription record);

}