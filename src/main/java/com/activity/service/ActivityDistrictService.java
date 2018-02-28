package com.activity.service;

import com.activity.model.ActivityDistrict;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-14
 */
public interface ActivityDistrictService {

    ActivityDistrict selectOne(Integer id);

    List<ActivityDistrict> selectList(ActivityDistrict record);

    int insert(ActivityDistrict record);

    int update(ActivityDistrict record);
}
