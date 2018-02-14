package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ky.bai on 2018/2/14 08:55
 */
@Entity
@Table(name = "activity_district")
public class ActivityDistrict implements Serializable {
    private static final long serialVersionUID = -9218347169609173452L;

    private Integer id;
    //区域名称
    private String name;
    //有效性
    private Boolean active;
    //创建时间
    private Date createDate;

    public ActivityDistrict() {
    }

    public ActivityDistrict(String name) {
        this.name = name;
    }

    public ActivityDistrict(String name, Boolean active) {
        this.name = name;
        this.active = active;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}