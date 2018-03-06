package com.activity.service.impl;

import com.activity.mapper.ActivityDistrictMapper;
import com.activity.model.ActivityDistrict;
import com.activity.service.ActivityDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-14
 */
@Service
public class ActivityDistrictServiceImpl implements ActivityDistrictService {

    @Autowired
    private ActivityDistrictMapper activityDistrictMapper;

    @Override
    public ActivityDistrict selectOne(Integer id) {
        ActivityDistrict district = activityDistrictMapper.selectOne(id);
        return ObjectUtils.isEmpty(district) ? new ActivityDistrict() : district;
    }

    @Override
    public List<ActivityDistrict> selectList(ActivityDistrict record) {
        return activityDistrictMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(ActivityDistrict record) {
        return activityDistrictMapper.insert(record);
    }

    @Override
    public int update(ActivityDistrict record) {
        return activityDistrictMapper.update(record);
    }
}
