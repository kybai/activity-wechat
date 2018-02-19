package com.activity.model;

import javax.persistence.*;

/**
 * 活动使用标签类
 *
 * @author Create by ky.bai on 2018-02-10 21:24
 */
@Entity
@Table(name = "activity_tag")
public class ActivityTag {

    private Integer id;
    //活动编号
    private Integer activityID;
    //使用姓名
    private Boolean useName;
    //使用性别
    private Boolean useSex;
    //使用联系电话
    private Boolean usePhone;
    //使用政治面貌
    private Boolean usePolitical;
    //使用工作单位
    private Boolean useCompany;
    //使用职务
    private Boolean useJob;
    //使用证件
    private Boolean useCard;
    //使用个人简介
    private Boolean useProfile;

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

    @Column(name = "use_name")
    public Boolean getUseName() {
        return useName;
    }

    @Column(name = "use_sex")
    public Boolean getUseSex() {
        return useSex;
    }

    @Column(name = "use_phone")
    public Boolean getUsePhone() {
        return usePhone;
    }

    @Column(name = "use_political")
    public Boolean getUsePolitical() {
        return usePolitical;
    }

    @Column(name = "use_company")
    public Boolean getUseCompany() {
        return useCompany;
    }

    @Column(name = "use_job")
    public Boolean getUseJob() {
        return useJob;
    }

    @Column(name = "use_card")
    public Boolean getUseCard() {
        return useCard;
    }

    @Column(name = "use_profile")
    public Boolean getUseProfile() {
        return useProfile;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public void setUseName(Boolean useName) {
        this.useName = useName;
    }

    public void setUseSex(Boolean useSex) {
        this.useSex = useSex;
    }

    public void setUsePhone(Boolean usePhone) {
        this.usePhone = usePhone;
    }

    public void setUsePolitical(Boolean usePolitical) {
        this.usePolitical = usePolitical;
    }

    public void setUseCompany(Boolean useCompany) {
        this.useCompany = useCompany;
    }

    public void setUseJob(Boolean useJob) {
        this.useJob = useJob;
    }

    public void setUseCard(Boolean useCard) {
        this.useCard = useCard;
    }

    public void setUseProfile(Boolean useProfile) {
        this.useProfile = useProfile;
    }
}
