package com.activity.service;

import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityDescription;
import com.activity.model.ActivityTag;
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

    public void update(ActivityPojo pojo);

    /**
     * Created by ky.bai on 2018-02-23 15:49
     *
     * @param record 活动编号
     *
     * @return 活动课程列表
     */
    public List<ActivityCourse> selectCourseList(ActivityCourse record);

    /**
     * Created by ky.bai on 2018-02-23 15:29
     *
     * @param record 活动编号
     *
     * @return 活动介绍
     */
    public ActivityDescription selectDesc(ActivityDescription record);

    /**
     * Created by ky.bai on 2018-02-23 15:29
     *
     * @param record 活动编号
     *
     * @return 活动标签
     */
    public ActivityTag selectTag(ActivityTag record);
}
