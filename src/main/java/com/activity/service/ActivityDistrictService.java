package com.activity.service;

import com.activity.model.ActivityDistrict;
import com.activity.pojo.BasePageList;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-14
 */
public interface ActivityDistrictService {

    public ActivityDistrict selectOne(Integer id);

    public List<ActivityDistrict> selectList(ActivityDistrict record);

    public PageInfo<ActivityDistrict> selectList(BasePageList page);

    public int insert(ActivityDistrict record);

    public int update(ActivityDistrict record);
}
