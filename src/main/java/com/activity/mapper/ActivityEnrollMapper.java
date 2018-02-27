package com.activity.mapper;

import com.activity.model.ActivityEnroll;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 16:49
 */
public interface ActivityEnrollMapper {

    public ActivityEnroll selectByPrimaryKey(Integer id);

    public List<ActivityEnroll> selectList(ActivityEnroll record);

    public int insert(ActivityEnroll record);

    public int update(ActivityEnroll record);
}
