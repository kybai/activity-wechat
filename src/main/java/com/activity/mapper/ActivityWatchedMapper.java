package com.activity.mapper;

import com.activity.model.ActivityWatched;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 17:11
 */
public interface ActivityWatchedMapper {

    public ActivityWatched selectByPrimaryKey(Integer id);

    public List<ActivityWatched> selectList(ActivityWatched record);

    public int insert(ActivityWatched record);

    public int delete(ActivityWatched record);
}
