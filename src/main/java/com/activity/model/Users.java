package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户类
 *
 * @author Created by ky.bai on 2018-02-12
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 3138399898572963721L;

    private Integer id;
    //昵称
    private String name;
    //性别
    private String sex;
    //城市
    private String city;
    //省份
    private String province;
    //国家
    private String country;
    //头像
    private String headImgUrl;
    //备注
    private String remark;
    //有效性(是否禁用)
    private Boolean active;
    //创建时间(首次登陆时间)
    private Timestamp createDate;

    //积分--非数据库字段
    private Integer score;
    //活动区域
    private Integer districtId;

    public Users() {
    }

    public Users(String name) {
        this.name = name;
    }

    public Users(Integer id, Boolean active) {
        this.id = id;
        this.active = active;
    }

    public Users(Integer id, Boolean active, Integer districtId) {
        this.id = id;
        this.active = active;
        this.districtId = districtId;
    }

    public Users(String name, String sex, String city, String province, String country, String headImgUrl, Boolean active, Timestamp createDate) {
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headImgUrl = headImgUrl;
        this.active = active;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    @Column(name = "head_img_url")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
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
    public Integer getScore() {
        return score;
    }

    @Transient
    public Integer getDistrictId() {
        return districtId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
