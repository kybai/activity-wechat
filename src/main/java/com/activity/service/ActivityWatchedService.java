package com.activity.service;

import com.activity.model.ActivityWatched;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-27
 */
public interface ActivityWatchedService {

    ActivityWatched selectById(Integer id);

    List<ActivityWatched> selectList(ActivityWatched record);

    int insert(ActivityWatched record);

}
