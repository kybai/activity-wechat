package com.activity.service.impl;

import com.activity.mapper.ActivityEnrollMapper;
import com.activity.model.ActivityEnroll;
import com.activity.service.ActivityEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Service
public class ActivityEnrollServiceImpl implements ActivityEnrollService {

    @Autowired
    private ActivityEnrollMapper activityEnrollMapper;

    @Override
    public ActivityEnroll selectById(Integer id) {
        return activityEnrollMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ActivityEnroll> selectList(ActivityEnroll record) {
        return activityEnrollMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(ActivityEnroll record) {
        return activityEnrollMapper.insert(record);
    }

    @Override
    @Transactional
    public int update(ActivityEnroll record) {
        return activityEnrollMapper.update(record);
    }
}
