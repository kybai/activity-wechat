package com.activity.service;

import com.activity.model.ActivityWatched;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
public interface ActivityWatchedService {

    public ActivityWatched selectById(Integer id);

    public List<ActivityWatched> selectList(ActivityWatched record);

    public int insert(ActivityWatched record);

}
