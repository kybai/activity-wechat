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

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityEnroll that = (ActivityEnroll) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (activityId != null ? !activityId.equals(that.activityId) : that.activityId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (political != null ? !political.equals(that.political) : that.political != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (job != null ? !job.equals(that.job) : that.job != null) return false;
        if (cardFace != null ? !cardFace.equals(that.cardFace) : that.cardFace != null) return false;
        if (cardBack != null ? !cardBack.equals(that.cardBack) : that.cardBack != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        return createDate != null ? createDate.equals(that.createDate) : that.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (political != null ? political.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (cardFace != null ? cardFace.hashCode() : 0);
        result = 31 * result + (cardBack != null ? cardBack.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
