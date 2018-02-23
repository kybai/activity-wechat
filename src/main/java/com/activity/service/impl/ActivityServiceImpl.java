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
import com.activity.pojo.BasePageList;
import com.activity.service.ActivityService;
import com.activity.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        Activity activity = activityMapper.selectOne(id);
        if (activity.getMaxLimit() == null) {
            activity.setMaxLimit(0);
        }
        return activity;
    }

    @Override
    public List<Activity> selectList(Activity record) {
        return activityMapper.selectList(record);
    }

    @Override
    public PageInfo<Activity> selectPage(BasePageList page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        return new PageInfo<>(selectList(new Activity(page.getName(), page.getOtherId())));
    }

    @Override
    @Transactional
    public int insert(Activity activity) {
        if (activity.getMaxLimit() == null) {
            activity.setMaxLimit(0);
        }
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
        tag.setActivityId(activity.getId());
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

    public void update(ActivityPojo pojo) {
        //1.保存活动，获取活动编号
        Activity activity = pojo.getActivity();
        activity.setActive(Boolean.TRUE);
        activityMapper.update(activity);

        //2.保存活动标签
        ActivityTag tag = pojo.getActivityTag();
        tag.setActivityId(activity.getId());
        activityTagMapper.updateByPrimaryKey(tag);

        //3.保存活动课程
        List<ActivityCourse> courseList = pojo.getCourseList();
        if (!ObjectUtils.isEmpty(courseList)) {
            for (ActivityCourse course : courseList) {
                course.setActive(Boolean.TRUE);
                activityCourseMapper.updateByPrimaryKey(course);
            }
        }

        //4.保存活动描述
        descriptionMapper.updateByPrimaryKeyWithBLOBs(new ActivityDescription(activity.getId(), pojo.getDesc()));
    }

    @Override
    public List<ActivityCourse> selectCourseList(ActivityCourse record) {
        return activityCourseMapper.selectList(record);
    }

    @Override
    public ActivityDescription selectDesc(ActivityDescription record) {
        List<ActivityDescription> list = descriptionMapper.selectList(record);
        return ObjectUtils.isEmpty(list) ? new ActivityDescription() : list.get(0);
    }

    @Override
    public ActivityTag selectTag(ActivityTag record) {
        List<ActivityTag> list = activityTagMapper.selectList(record);
        return ObjectUtils.isEmpty(list) ? new ActivityTag() : list.get(0);
    }
}
