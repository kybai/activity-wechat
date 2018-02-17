package com.activity.service.impl;

import com.activity.mapper.ActivityMapper;
import com.activity.model.Activity;
import com.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-16
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

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
}
