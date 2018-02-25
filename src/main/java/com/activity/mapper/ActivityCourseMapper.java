package com.activity.mapper;

import com.activity.model.ActivityCourse;

import java.util.List;

public interface ActivityCourseMapper {

    ActivityCourse selectByPrimaryKey(Integer id);

    List<ActivityCourse> selectList(ActivityCourse record);

    int insert(ActivityCourse record);

    int updateByPrimaryKey(ActivityCourse record);

}