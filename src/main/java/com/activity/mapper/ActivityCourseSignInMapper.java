package com.activity.mapper;

import com.activity.model.ActivityCourseSignIn;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 16:49
 */
public interface ActivityCourseSignInMapper {

    public ActivityCourseSignIn selectByPrimaryKey(Integer id);

    public List<ActivityCourseSignIn> selectList(ActivityCourseSignIn record);

    public int insert(ActivityCourseSignIn record);

}
