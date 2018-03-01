package com.activity.service;

import com.activity.model.ActivityCourseSignIn;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-28 17:59
 */
public interface ActivityCourseSignInService {

    public List<ActivityCourseSignIn> selectList(ActivityCourseSignIn record);

    public int insert(ActivityCourseSignIn record);

    /**
     * Created by ky.bai on 2018-03-01 17:10
     *
     * @param record 活动编号、用户编号
     * @return 某活动下用户的签到记录
     */
    List<ActivityCourseSignIn> selectSignList(ActivityCourseSignIn record);

}
