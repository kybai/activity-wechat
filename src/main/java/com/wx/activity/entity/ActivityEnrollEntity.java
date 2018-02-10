package com.wx.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 活动报名类
 *
 * @author Create by ky.bai on 2018-02-10 21:44
 */
@Entity
@Table(name = "ActivityEnroll")
public class ActivityEnrollEntity implements Serializable {
    private static final long serialVersionUID = 8319965561102617410L;

    private Integer id;
    //活动编号
    private Integer activityID;
    //用户编号
    private Integer openid;
    //姓名
    private String name;
    //性别
    private String sex;
    //联系电话
    private String phone;
    //政治面貌
    private String political;
    //工作单位
    private String company;
    //职务
    private String job;
    //身份证正面文件(文件编号)
    private Integer cardFace;
    //身份证反面文件(文件编号)
    private Integer cardBack;

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

    @Column(name = "openid")
    public Integer getOpenid() {
        return openid;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
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
}
