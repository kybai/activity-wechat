package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 活动描述
 * Created by ky.bai on 2018/2/20 23:12
 */
@Entity
@Table(name = "activity_description")
public class ActivityDescription implements Serializable {
    private static final long serialVersionUID = 570414699534520318L;
    //编号
    private Integer id;
    //活动编号
    private Integer activityId;
    //描述
    private String description;

    public ActivityDescription() {
    }

    public ActivityDescription(Integer activityId, String description) {
        this.activityId = activityId;
        this.description = description;
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

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}