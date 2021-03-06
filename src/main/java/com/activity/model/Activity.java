package com.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "activity")
public class Activity implements Serializable {
    private static final long serialVersionUID = -9036101568327677985L;

    private Integer id;
    //标题
    private String title;
    //区域编号
    private Integer districtId;
    //活动地址
    private String address;
    //开始时间
    private Timestamp beginTime;
    //结束时间
    private Timestamp endTime;
    //人数限制
    private Integer maxLimit;
    //上传文件编号
    private Integer uploadFileId;
    //有效性
    private Boolean active;
    //创建时间
    private Timestamp createDate;

    /*非数据库字段*/

    //点赞人数
    private int thumbupTotal;
    //查看人数
    private int watchedTotal;
    //报名人数
    private int enrollTotal;

    public Activity() {
    }

    public Activity(Boolean active) {
        this.active = active;
    }

    public Activity(String title, Integer districtId) {
        this.title = title;
        this.districtId = districtId;
    }

    public Activity(Integer districtId, Boolean active, Timestamp endTime) {
        this.districtId = districtId;
        this.active = active;
        this.endTime = endTime;
    }

    public Activity(Integer id, String title, Integer districtId, String address, Timestamp beginTime, Timestamp endTime, Integer maxLimit, Integer uploadFileId, Boolean active) {
        this.id = id;
        this.title = title;
        this.districtId = districtId;
        this.address = address;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.maxLimit = maxLimit;
        this.uploadFileId = uploadFileId;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "district_id")
    public Integer getDistrictId() {
        return districtId;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    @Column(name = "begin_time")
    public Timestamp getBeginTime() {
        return beginTime;
    }

    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    @Column(name = "max_limit")
    public Integer getMaxLimit() {
        return maxLimit;
    }

    @Column(name = "upload_file_id")
    public Integer getUploadFileId() {
        return uploadFileId;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    @Column(name = "create_Timestamp")
    public Timestamp getCreateDate() {
        return createDate;
    }

    @Transient
    public int getThumbupTotal() {
        return thumbupTotal;
    }

    @Transient
    public int getWatchedTotal() {
        return watchedTotal;
    }

    @Transient
    public int getEnrollTotal() {
        return enrollTotal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void setUploadFileId(Integer uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setThumbupTotal(int thumbupTotal) {
        this.thumbupTotal = thumbupTotal;
    }

    public void setWatchedTotal(int watchedTotal) {
        this.watchedTotal = watchedTotal;
    }

    public void setEnrollTotal(int enrollTotal) {
        this.enrollTotal = enrollTotal;
    }
}