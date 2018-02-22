package com.activity.service;

import com.activity.model.Activity;
import com.activity.pojo.ActivityPojo;
import com.activity.pojo.BasePageList;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-16
 */
public interface ActivityService {

    public int insert(Activity activity);

    public int update(Activity activity);

    public Activity selectOne(Integer id);

    public List<Activity> selectList(Activity record);

    public PageInfo<Activity> selectPage(BasePageList page);

    public void save(ActivityPojo pojo);
}
