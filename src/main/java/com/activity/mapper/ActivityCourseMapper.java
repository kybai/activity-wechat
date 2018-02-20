package com.activity.mapper;

import com.activity.model.ActivityCourse;

import java.util.List;

public interface ActivityCourseMapper {

    int insert(ActivityCourse record);

    ActivityCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ActivityCourse record);

    int insertList(List<ActivityCourse> list);
}