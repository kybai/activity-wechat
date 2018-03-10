package com.activity.service.impl;

import com.activity.mapper.ActivityWatchedMapper;
import com.activity.model.ActivityWatched;
import com.activity.service.ActivityWatchedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Service
public class ActivityWatchedServiceImpl implements ActivityWatchedService {

    @Autowired
    private ActivityWatchedMapper activityWatchedMapper;

    @Override
    public ActivityWatched selectById(Integer id) {
        ActivityWatched watched = activityWatchedMapper.selectByPrimaryKey(id);
        return ObjectUtils.isEmpty(watched) ? new ActivityWatched() : watched;
    }

    @Override
    public List<ActivityWatched> selectList(ActivityWatched record) {
        return activityWatchedMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(ActivityWatched record) {
        return activityWatchedMapper.insert(record);
    }

    @Override
    public int countWatchedTotal(Integer activityId) {
        return activityWatchedMapper.countWatchedTotal(activityId);
    }

}
