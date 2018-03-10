package com.activity.mapper;

import com.activity.model.ActivityEnroll;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 16:49
 */
public interface ActivityEnrollMapper {

    ActivityEnroll selectByPrimaryKey(Integer id);

    List<ActivityEnroll> selectList(ActivityEnroll record);

    int insert(ActivityEnroll record);

    int update(ActivityEnroll record);

    int countEnrollRanking(Integer activityId);
}
