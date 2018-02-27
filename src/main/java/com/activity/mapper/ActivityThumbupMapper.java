package com.activity.mapper;

import com.activity.model.ActivityThumbup;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 17:11
 */
public interface ActivityThumbupMapper {

    public ActivityThumbup selectByPrimaryKey(Integer id);

    public List<ActivityThumbup> selectList(ActivityThumbup record);

    public int insert(ActivityThumbup record);

    public int delete(ActivityThumbup record);
}
