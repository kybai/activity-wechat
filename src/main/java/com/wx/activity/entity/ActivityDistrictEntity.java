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
 * 活动区域表
 *
 * @author Create by ky.bai on 2018-02-10 21:03
 */
@Entity
@Table(name = "District")
public class ActivityDistrictEntity implements Serializable {
    private static final long serialVersionUID = 2257824887461772180L;

    private Integer id;
    //区域名称
    private String name;
    //有效性
    private Boolean active;
    //创建时间
    private Date createDate;

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
