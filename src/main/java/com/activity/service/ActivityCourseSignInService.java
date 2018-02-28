package com.activity.service;

import com.activity.model.ActivityCourseSignIn;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-28 17:59
 */
public interface ActivityCourseSignInService {

    public List<ActivityCourseSignIn> selectList(ActivityCourseSignIn record);

    public int insert(ActivityCourseSignIn record);

}
