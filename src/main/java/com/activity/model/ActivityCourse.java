package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 活动课程
 * Created by ky.bai on 2018/2/19 20:01
 */
@Entity
@Table(name = "activity_course")
public class ActivityCourse implements Serializable {
    private static final long serialVersionUID = 1229139653216757995L;

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

    //用户签到时间 -- 非数据库字段
    private Timestamp signInTime;
    //用户签到数据
    private int signTotal;
    //课程报名数量
    private int enrollTotal;

    public ActivityCourse() {
    }

    public ActivityCourse(Integer activityId) {
        this.activityId = activityId;
    }

    public ActivityCourse(Integer activityId, Boolean active) {
        this.activityId = activityId;
        this.active = active;
    }

    public ActivityCourse(Timestamp beginTime, Timestamp endTime, Boolean active) {
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    @Transient
    public Timestamp getSignInTime() {
        return signInTime;
    }

    @Transient
    public int getSignTotal() {
        return signTotal;
    }

    @Transient
    public int getEnrollTotal() {
        return enrollTotal;
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

    public void setSignInTime(Timestamp signInTime) {
        this.signInTime = signInTime;
    }

    public void setSignTotal(int signTotal) {
        this.signTotal = signTotal;
    }

    public void setEnrollTotal(int enrollTotal) {
        this.enrollTotal = enrollTotal;
    }
}