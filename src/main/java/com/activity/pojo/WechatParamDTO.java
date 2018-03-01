package com.activity.pojo;

import java.io.Serializable;

/**
 * @author Create by ky.bai on 2018-03-01 17:25
 */
public class WechatParamDTO implements Serializable {
    private static final long serialVersionUID = 1950966357175070237L;

    private Integer userId;
    private Integer activityId;
    private Integer courseId;
    private Integer districtId;
    private Boolean active;

    public WechatParamDTO() {
    }

    public WechatParamDTO(Integer userId, Integer activityId, Boolean active) {
        this.userId = userId;
        this.activityId = activityId;
        this.active = active;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
