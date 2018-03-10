package com.activity.mapper;

import com.activity.model.ActivityWatched;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 17:11
 */
public interface ActivityWatchedMapper {

    ActivityWatched selectByPrimaryKey(Integer id);

    List<ActivityWatched> selectList(ActivityWatched record);

    int insert(ActivityWatched record);

    int countWatchedTotal(Integer activityId);
}
