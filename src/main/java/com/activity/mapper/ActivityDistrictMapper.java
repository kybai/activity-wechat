package com.activity.mapper;

import com.activity.model.ActivityDistrict;

import java.util.List;

public interface ActivityDistrictMapper {

    ActivityDistrict selectOne(Integer id);

    List<ActivityDistrict> selectList(ActivityDistrict record);

    int insert(ActivityDistrict record);

    int update(ActivityDistrict record);
}