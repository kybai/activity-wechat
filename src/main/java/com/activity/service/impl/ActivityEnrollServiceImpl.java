package com.activity.service.impl;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.mapper.ActivityEnrollMapper;
import com.activity.mapper.UsersScoreMapper;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityEnroll;
import com.activity.model.UsersScore;
import com.activity.service.ActivityEnrollService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Service
public class ActivityEnrollServiceImpl implements ActivityEnrollService {

    @Autowired
    private ActivityEnrollMapper activityEnrollMapper;

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private UsersScoreMapper usersScoreMapper;

    @Override
    public ActivityEnroll selectById(Integer id) {
        ActivityEnroll enroll = activityEnrollMapper.selectByPrimaryKey(id);
        return ObjectUtils.isEmpty(enroll) ? new ActivityEnroll() : enroll;
    }

    @Override
    public List<ActivityEnroll> selectList(ActivityEnroll record) {
        return activityEnrollMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(ActivityEnroll record) {
        List<ActivityCourse> courses = activityCourseMapper.selectList(new ActivityCourse(record.getActivityId()));
        //添加积分
        if (!ObjectUtils.isEmpty(courses)) {
            for (ActivityCourse course : courses) {
                String reason = "报名课程：" + course.getName();
                usersScoreMapper.insert(new UsersScore(record.getUserId(), Constants.SCORE_SIGN_COURSE, reason, record.getActivityId(), course.getId(), DateUtils.getCurrentTimestamp()));
            }
        }
        int ranking = activityEnrollMapper.countEnrollRanking(record.getActivityId());
        //课程报名
        record.setRanking(ranking + 1);
        record.setActive(Boolean.TRUE);
        record.setCreateDate(DateUtils.getCurrentTimestamp());
        return activityEnrollMapper.insert(record);
    }

    @Override
    @Transactional
    public int update(ActivityEnroll record) {
        return activityEnrollMapper.update(record);
    }
}
