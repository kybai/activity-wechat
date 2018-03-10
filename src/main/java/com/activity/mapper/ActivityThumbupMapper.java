package com.activity.mapper;

import com.activity.model.ActivityThumbup;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 17:11
 */
public interface ActivityThumbupMapper {

    ActivityThumbup selectByPrimaryKey(Integer id);

    List<ActivityThumbup> selectList(ActivityThumbup record);

    int countThumbupTotal(Integer activityId);

    int insert(ActivityThumbup record);

    int delete(ActivityThumbup record);
}
