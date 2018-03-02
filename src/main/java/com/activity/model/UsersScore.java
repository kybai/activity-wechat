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
 * 用户积分
 *
 * @author Create by ky.bai on 2018-02-27 17:31
 */
@Entity
@Table(name = "users_score")
public class UsersScore implements Serializable {
    private static final long serialVersionUID = 7251493519106120788L;

    private Integer id;
    //用户编号
    private Integer userId;
    //加减积分
    private Integer score;
    //加减积分原因
    private String reason;
    //活动编号
    private Integer activityId;
    //课程编号
    private Integer courseId;
    //创建日期
    private Timestamp createDate;

    public UsersScore() {
    }

    public UsersScore(Integer userId) {
        this.userId = userId;
    }

    public UsersScore(Integer userId, Integer score, String reason, Integer activityId, Integer courseId, Timestamp createDate) {
        this.userId = userId;
        this.score = score;
        this.reason = reason;
        this.activityId = activityId;
        this.courseId = courseId;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersScore that = (UsersScore) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(score, that.score) && Objects.equals(reason, that.reason) && Objects.equals(activityId, that
                .activityId) && Objects.equals(courseId, that.courseId) && Objects.equals(createDate, that.createDate);
    }

    public int hashCode() {

        return Objects.hash(id, userId, score, reason, activityId, courseId, createDate);
    }
}
