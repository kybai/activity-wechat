package com.activity.service.impl;

import com.activity.mapper.ActivityThumbupMapper;
import com.activity.model.ActivityThumbup;
import com.activity.service.ActivityThumbupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Service
public class ActivityThumbupServiceImpl implements ActivityThumbupService {

    @Autowired
    private ActivityThumbupMapper activityThumbupMapper;

    @Override
    public ActivityThumbup selectById(Integer id) {
        return activityThumbupMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ActivityThumbup> selectList(ActivityThumbup record) {
        return activityThumbupMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(ActivityThumbup record) {
        return activityThumbupMapper.insert(record);
    }

    @Override
    @Transactional
    public int delete(ActivityThumbup record) {
        return activityThumbupMapper.delete(record);
    }
}
