package com.wx.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动类
 *
 * @author Create by ky.bai on 2018-02-10 20:48
 */
@Entity
@Table(name = "activity")
public class ActivityEntity implements Serializable {
    private static final long serialVersionUID = -3349287691785504379L;

    private Integer id;
    //活动标题
    private String title;
    //活动区域编号
    private Integer districtID;
    //活动开始时间
    private Date beginTime;
    //活动结束时间
    private Date endTime;
    //人数限制,默认不限制
    private Integer maxLimit;
    //封面编号(文件编号)
    private Integer uploadFileID;
    //有效性
    private Boolean active;
    //创建日期
    private Date createDate;

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
    public Integer getDistrictID() {
        return districtID;
    }

    @Column(name = "begin_time")
    public Date getBeginTime() {
        return beginTime;
    }

    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    @Column(name = "max_limit")
    public Integer getMaxLimit() {
        return maxLimit;
    }

    @Column(name = "upload_file_id")
    public Integer getUploadFileID() {
        return uploadFileID;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDistrictID(Integer districtID) {
        this.districtID = districtID;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void setUploadFileID(Integer uploadFileID) {
        this.uploadFileID = uploadFileID;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
