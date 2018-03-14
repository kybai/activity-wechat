package com.activity.service.impl;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.mapper.ActivityEnrollMapper;
import com.activity.mapper.ActivityMapper;
import com.activity.mapper.UsersScoreMapper;
import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityEnroll;
import com.activity.model.UsersScore;
import com.activity.service.ActivityEnrollService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ActivityEnrollServiceImpl.class);

    @Autowired
    private ActivityEnrollMapper activityEnrollMapper;

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private UsersScoreMapper usersScoreMapper;

    @Autowired
    private ActivityMapper activityMapper;

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
        Activity activity = activityMapper.selectOne(record.getActivityId());
        int ranking = activityEnrollMapper.countEnrollRanking(record.getActivityId());
        if (activity.getMaxLimit() == null || activity.getMaxLimit() == 0 || ranking < activity.getMaxLimit()) {
            List<ActivityCourse> courses = activityCourseMapper.selectList(new ActivityCourse(record.getActivityId()));
            if (!ObjectUtils.isEmpty(courses)) { //添加积分
                for (ActivityCourse course : courses) {
                    String reason = "报名课程：" + course.getName();
                    usersScoreMapper.insert(new UsersScore(record.getUserId(), Constants.SCORE_SIGN_COURSE, reason, record.getActivityId(), course.getId(), DateUtils.getCurrentTimestamp()));
                }
            }
            //课程报名
            record.setRanking(ranking + 1);
            record.setActive(Boolean.TRUE);
            record.setCreateDate(DateUtils.getCurrentTimestamp());
            return activityEnrollMapper.insert(record);
        }
        return -1;
    }

    @Override
    @Transactional
    public int update(ActivityEnroll record) {
        return activityEnrollMapper.update(record);
    }
}
