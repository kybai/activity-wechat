package com.activity.service.impl;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.mapper.ActivityCourseSignInMapper;
import com.activity.mapper.ActivityMapper;
import com.activity.mapper.UsersScoreMapper;
import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityCourseSignIn;
import com.activity.model.UsersScore;
import com.activity.service.ActivityCourseSignInService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-28 18:00
 */
@Service
public class ActivityCourseSignInServiceImpl implements ActivityCourseSignInService {

    @Autowired
    private ActivityCourseSignInMapper activityCourseSignInMapper;

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UsersScoreMapper usersScoreMapper;

    @Override
    public List<ActivityCourseSignIn> selectList(ActivityCourseSignIn record) {
        return activityCourseSignInMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(ActivityCourseSignIn record) {
        ActivityCourse course = activityCourseMapper.selectByPrimaryKey(record.getCourseId());
        Activity activity = activityMapper.selectOne(course.getActivityId());
        //添加积分
        String reason = "参加课程：" + course.getName();
        usersScoreMapper.insert(new UsersScore(record.getUserId(), Constants.SCORE_SIGN_COURSE, reason, activity.getId(), record.getCourseId(), DateUtils.getCurrentTimestamp()));
        //课程签到
        return activityCourseSignInMapper.insert(record);
    }

    @Override
    public List<ActivityCourseSignIn> selectSignList(ActivityCourseSignIn record) {
        return null;
    }
}
