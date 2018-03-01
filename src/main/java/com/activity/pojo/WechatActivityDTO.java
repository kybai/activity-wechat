package com.activity.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Create by ky.bai on 2018-03-01 11:19
 */
public class WechatActivityDTO implements Serializable {
    private static final long serialVersionUID = -7088662147673493663L;

    //活动编号
    private Integer id;
    //活动标题
    private String title;
    //活动结束时间
    private Timestamp beginTime;
    //活动开始时间
    private Timestamp endTime;
    //活动地址
    private String address;
    //封面路径
    private String coverPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
}
