package com.activity.service.etl.impl;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.mapper.ActivityCourseSignInMapper;
import com.activity.mapper.ActivityEnrollMapper;
import com.activity.mapper.UsersScoreMapper;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityCourseSignIn;
import com.activity.model.ActivityEnroll;
import com.activity.model.UsersScore;
import com.activity.service.etl.ETLTask;
import com.activity.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动课程结算
 *
 * @author Create by ky.bai on 2018-03-01 15:48
 */
@Service
public class UserScoreSettlementETLTask extends ETLTask {

    private static final String ETL_TASK_NAME = "UserScoreSettlementETLTask";

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private ActivityCourseSignInMapper activityCourseSignInMapper;

    @Autowired
    private ActivityEnrollMapper activityEnrollMapper;

    @Autowired
    private UsersScoreMapper usersScoreMapper;

    public UserScoreSettlementETLTask() {
        super(ETL_TASK_NAME);
    }

    @Override
    @Transactional
    public void taskExecute(Timestamp startTime, Timestamp endTime) {
        //获取结束但未结算的课程
        List<ActivityCourse> courses = activityCourseMapper.selectListByTime(new ActivityCourse(startTime, endTime, Boolean.TRUE));
        if (!ObjectUtils.isEmpty(courses)) {
            List<UsersScore> list = new ArrayList<>();
            for (ActivityCourse course : courses) {
                //获取课程的报名人员
                List<ActivityEnroll> enrolls = activityEnrollMapper.selectList(new ActivityEnroll(course.getActivityId(), Boolean.TRUE));
                //获取课程的签到人员
                List<ActivityCourseSignIn> signIns = activityCourseSignInMapper.selectList(new ActivityCourseSignIn(course.getId()));

                if (!ObjectUtils.isEmpty(enrolls)) {
                    for (ActivityEnroll enroll : enrolls) {
                        boolean sign = false;//是否签到
                        if (!ObjectUtils.isEmpty(signIns)) {
                            for (ActivityCourseSignIn signIn : signIns) {
                                if (signIn.getUserId().toString().equals(enroll.getUserId().toString())) {
                                    sign = true;
                                    break;
                                }
                            }
                        }
                        //缺席活动课程人员扣2分
                        if (!sign)
                            list.add(new UsersScore(enroll.getUserId(), Constants.SCORE_MISS_COURSE, "缺席课程:" + course.getName(), course.getActivityId(), course.getId(), endTime));
                    }
                }
            }
            if (!ObjectUtils.isEmpty(list)) {
                usersScoreMapper.insertList(list);
            }
        }

    }
}
