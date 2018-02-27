package com.activity.service;

import com.activity.model.ActivityEnroll;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
public interface ActivityEnrollService {

    public ActivityEnroll selectById(Integer id);

    public List<ActivityEnroll> selectList(ActivityEnroll record);

    public int insert(ActivityEnroll record);

    public int update(ActivityEnroll record);
}
