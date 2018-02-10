package com.wx.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 活动简介
 *
 * @author Create by ky.bai on 2018-02-10 21:21
 */
@Entity
@Table(name = "Description")
public class ActivityDescriptionEntity implements Serializable {
    private static final long serialVersionUID = 2871485149275980586L;

    private Integer id;
    //活动编号
    private Integer activityID;
    //活动简介
    private String description;

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

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
