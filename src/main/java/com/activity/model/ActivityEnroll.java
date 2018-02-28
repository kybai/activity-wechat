package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 活动登记/报名
 *
 * @author Create by ky.bai on 2018-02-27 15:19
 */
@Entity
@Table(name = "activity_enroll")
public class ActivityEnroll implements Serializable {
    private static final long serialVersionUID = 705103600521234171L;

    private Integer id;
    //活动编号
    private Integer activityId;
    //用户编号
    private String userId;
    //姓名
    private String name;
    //性别
    private String sex;
    //联系方式
    private String phone;
    //政治面貌
    private String political;
    //工作单位
    private String company;
    //职务
    private String job;
    //证件正面对应文件编号
    private Integer cardFace;
    //证件反面对应文件编号
    private Integer cardBack;
    //个人简介
    private String profile;
    //有效性
    private Boolean active;
    //创建日期
    private Timestamp createDate;

    public ActivityEnroll() {
    }

    public ActivityEnroll(Integer activityId) {
        this.activityId = activityId;
    }

    public ActivityEnroll(Integer activityId, Boolean active) {
        this.activityId = activityId;
        this.active = active;
    }

    public ActivityEnroll(Integer activityId, Boolean active, String name) {
        this.activityId = activityId;
        this.active = active;
        this.name = name;
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
    public String getUserId() {
        return userId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(name = "political")
    public String getPolitical() {
        return political;
    }

    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    @Column(name = "job")
    public String getJob() {
        return job;
    }

    @Column(name = "card_face")
    public Integer getCardFace() {
        return cardFace;
    }

    @Column(name = "card_back")
    public Integer getCardBack() {
        return cardBack;
    }

    @Column(name = "profile")
    public String getProfile() {
        return profile;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setCardFace(Integer cardFace) {
        this.cardFace = cardFace;
    }

    public void setCardBack(Integer cardBack) {
        this.cardBack = cardBack;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
