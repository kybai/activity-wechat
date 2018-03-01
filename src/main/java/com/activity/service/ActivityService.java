package com.activity.service;

import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityDescription;
import com.activity.model.ActivityTag;
import com.activity.pojo.ActivityPojo;
import com.activity.pojo.BasePageList;
import com.activity.pojo.WechatActivityDTO;
import com.activity.pojo.WechatPojo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-16
 */
public interface ActivityService {

    int insert(Activity activity);

    int update(Activity activity);

    Activity selectOne(Integer id);

    List<Activity> selectList(Activity record);

    PageInfo<Activity> selectPage(BasePageList page);

    void save(ActivityPojo pojo);

    void update(ActivityPojo pojo);

    /**
     * Created by ky.bai on 2018-02-23 15:49
     *
     * @param record 活动编号
     *
     * @return 活动课程列表
     */
    List<ActivityCourse> selectCourseList(ActivityCourse record);

    /**
     * Created by ky.bai on 2018-02-23 15:29
     *
     * @param record 活动编号
     *
     * @return 活动介绍
     */
    ActivityDescription selectDesc(ActivityDescription record);

    /**
     * Created by ky.bai on 2018-02-23 15:29
     *
     * @param record 活动编号
     *
     * @return 活动标签
     */
    ActivityTag selectTag(ActivityTag record);

    /**
     * Created by ky.bai on 2018-03-01 11:27
     *
     * @param pojo 参数
     *
     * @return 微信端活动列表(未结束)
     */
    List<WechatActivityDTO> selectWechatList(WechatPojo pojo);

    /**
     * Created by ky.bai on 2018-03-01 13:16
     *
     * @param pojo 参数
     *
     * @return 微信端活动回顾
     */
    List<WechatActivityDTO> selectWechatReviewList(WechatPojo pojo);

    /**
     * Created by ky.bai on 2018-03-01 13:16
     *
     * @param pojo 参数
     *
     * @return 微信端个人参与活动
     */
    List<WechatActivityDTO> selectUserWechatList(WechatPojo pojo);
}
