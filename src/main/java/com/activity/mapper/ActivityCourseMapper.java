package com.activity.mapper;

import com.activity.model.ActivityCourse;
import com.activity.pojo.WechatParamDTO;

import java.util.List;

public interface ActivityCourseMapper {

    ActivityCourse selectByPrimaryKey(Integer id);

    List<ActivityCourse> selectList(ActivityCourse record);

    //根据时间段获取已结束的课程
    List<ActivityCourse> selectListByTime(ActivityCourse record);

    //获取某活动下用户的签到记录
    List<ActivityCourse> selectSignList(WechatParamDTO record);

    int insert(ActivityCourse record);

    int updateByPrimaryKey(ActivityCourse record);

}