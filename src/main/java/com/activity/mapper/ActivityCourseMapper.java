package com.activity.mapper;

import com.activity.model.ActivityCourse;

public interface ActivityCourseMapper {

    int insert(ActivityCourse record);

    ActivityCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ActivityCourse record);
}