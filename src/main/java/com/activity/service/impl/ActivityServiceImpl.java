package com.activity.service.impl;

import com.activity.mapper.ActivityCourseMapper;
import com.activity.mapper.ActivityDescriptionMapper;
import com.activity.mapper.ActivityMapper;
import com.activity.mapper.ActivityTagMapper;
import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityDescription;
import com.activity.model.ActivityTag;
import com.activity.pojo.ActivityPojo;
import com.activity.service.ActivityService;
import com.activity.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-16
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Autowired
    private ActivityTagMapper activityTagMapper;

    @Autowired
    private ActivityDescriptionMapper descriptionMapper;

    @Override
    public Activity selectOne(Integer id) {
        return activityMapper.selectOne(id);
    }

    @Override
    public List<Activity> selectList(Activity record) {
        return null;
    }

    @Override
    @Transactional
    public int insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    @Transactional
    public int update(Activity activity) {
        return activityMapper.update(activity);
    }

    @Override
    @Transactional
    public void save(ActivityPojo pojo) {
        Timestamp currentTimestamp = DateUtils.getCurrentTimestamp();
        //1.保存活动，获取活动编号
        Activity activity = pojo.getActivity();
        activity.setActive(Boolean.TRUE);
        activity.setCreateDate(currentTimestamp);
        activityMapper.insert(activity);

        //2.保存活动标签
        ActivityTag tag = pojo.getActivityTag();
        tag.setActivityID(activity.getId());
        activityTagMapper.insert(tag);

        //3.保存活动课程
        List<ActivityCourse> courseList = pojo.getCourseList();
        if (!ObjectUtils.isEmpty(courseList)) {
            for (ActivityCourse course : courseList) {
                course.setActivityId(activity.getId());
                course.setActive(Boolean.TRUE);
                course.setCreateDate(currentTimestamp);
            }
            activityCourseMapper.insertList(courseList);
        }

        //4.保存活动描述
        descriptionMapper.insert(new ActivityDescription(activity.getId(), pojo.getDesc()));

    }
}
