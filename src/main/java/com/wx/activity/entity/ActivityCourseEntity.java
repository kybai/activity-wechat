package com.wx.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动课程类
 *
 * @author Create by ky.bai on 2018-02-10 21:58
 */
@Entity
@Table(name = "Course")
public class ActivityCourseEntity implements Serializable {
    private static final long serialVersionUID = 3176855541942249409L;

    private Integer id;
    //活动编号
    private Integer activityID;
    //课程名称
    private String name;
    //课程开始时间
    private Date beginTime;
    //课程结束时间
    private Date endTime;
    //有效性
    private Boolean active;
    //创建日期
    private Date createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "activity_id")
    public Integer getActivityID() {
        return activityID;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "begin_time")
    public Date getBeginTime() {
        return beginTime;
    }

    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
