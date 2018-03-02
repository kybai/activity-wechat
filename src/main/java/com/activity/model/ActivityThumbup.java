package com.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 活动点赞日志
 *
 * @author Create by ky.bai on 2018-02-27 17:09
 */
@Entity
@Table(name = "activity_thumbup")
public class ActivityThumbup implements Serializable {
    private static final long serialVersionUID = -599116648010419905L;

    private Integer id;
    //活动编号
    private Integer activityId;
    //用户编号
    private Integer userId;
    //点赞日期
    private Timestamp upTime;

    public ActivityThumbup() {
    }

    public ActivityThumbup(Integer activityId) {
        this.activityId = activityId;
    }

    public ActivityThumbup(Integer activityId, Integer userId) {
        this.activityId = activityId;
        this.userId = userId;
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

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "up_time")
    public Timestamp getUpTime() {
        return upTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUpTime(Timestamp upTime) {
        this.upTime = upTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityThumbup that = (ActivityThumbup) o;
        return Objects.equals(id, that.id) && Objects.equals(activityId, that.activityId) && Objects.equals(userId, that.userId) && Objects.equals(upTime, that.upTime);
    }

    public int hashCode() {

        return Objects.hash(id, activityId, userId, upTime);
    }
}
