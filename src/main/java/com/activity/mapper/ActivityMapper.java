package com.activity.mapper;

import com.activity.model.Activity;
import com.activity.model.Users;
import com.activity.pojo.WechatActivityDTO;

import java.util.List;

public interface ActivityMapper {

    int insert(Activity record);

    int update(Activity record);

    Activity selectOne(Integer id);

    List<Activity> selectList(Activity record);

    List<WechatActivityDTO> selectWechatList(Activity record);

    List<WechatActivityDTO> selectWechatReviewList(Activity record);

    List<WechatActivityDTO> selectUserWechatList(Users record);
}