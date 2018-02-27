package com.activity.service;

import com.activity.model.ActivityThumbup;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
public interface ActivityThumbupService {

    public ActivityThumbup selectById(Integer id);

    public List<ActivityThumbup> selectList(ActivityThumbup record);

    public int insert(ActivityThumbup record);

    public int delete(ActivityThumbup record);
}
