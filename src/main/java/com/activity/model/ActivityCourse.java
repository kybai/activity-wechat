package com.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 活动课程
 * Created by ky.bai on 2018/2/19 20:01
 */
@Entity
@Table(name = "activity_course")
public class ActivityCourse {

    //编号
    private Integer id;
    //活动编号
    private Integer activityId;
    //课程名称
    private String name;
    //课程开始时间
    private Timestamp beginTime;
    //课程结束时间
    private Timestamp endTime;
    //有效性
    private Boolean active;
    //创建时间
    private Timestamp createDate;

    public ActivityCourse() {
    }

    public ActivityCourse(Integer activityId) {
        this.activityId = activityId;
    }

    public ActivityCourse(Integer activityId, Boolean active) {
        this.activityId = activityId;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "begin_time")
    public Timestamp getBeginTime() {
        return beginTime;
    }

    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}