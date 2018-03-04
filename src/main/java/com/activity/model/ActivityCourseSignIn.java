package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 活动课程签到
 *
 * @author Created by ky.bai on 2018-02-27
 */
@Entity
@Table(name = "activity_course_signin")
public class ActivityCourseSignIn implements Serializable {
    private static final long serialVersionUID = -4644576983077385925L;

    private Integer id;
    //课程编号
    private Integer courseId;
    //用户编号
    private Integer userId;
    //签到时间
    private Timestamp signInTime;

    public ActivityCourseSignIn() {
    }

    public ActivityCourseSignIn(Integer courseId) {
        this.courseId = courseId;
    }

    public ActivityCourseSignIn(Integer userId, Integer courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "sign_in_time")
    public Timestamp getSignInTime() {
        return signInTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSignInTime(Timestamp signInTime) {
        this.signInTime = signInTime;
    }

}
