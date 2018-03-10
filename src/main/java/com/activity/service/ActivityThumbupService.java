package com.activity.service;

import com.activity.model.ActivityThumbup;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
public interface ActivityThumbupService {

    ActivityThumbup selectById(Integer id);

    List<ActivityThumbup> selectList(ActivityThumbup record);

    int countThumbupTotal(Integer activityId);

    int insert(ActivityThumbup record);

    int delete(ActivityThumbup record);

}
