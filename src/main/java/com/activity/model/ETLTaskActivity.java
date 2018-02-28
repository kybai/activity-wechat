package com.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 定时任务记录实体类
 *
 * @author Create by ky.bai on 2018-02-28 18:29
 */
@Entity
@Table(name = "etl_task_activity")
public class ETLTaskActivity implements Serializable {
    private static final long serialVersionUID = -706545693606654014L;

    private Integer id;
    //名称
    private String name;
    //成功时间
    private Timestamp successTime;
    //是否成功
    private boolean success;
    //创建日期
    private Timestamp createdDate;
    //修改日期
    private Timestamp modifiedDate;

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

    @Column(name = "success_time")
    public Timestamp getSuccessTime() {
        return successTime;
    }

    @Column(name = "success")
    public boolean isSuccess() {
        return success;
    }

    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Column(name = "modified_date")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuccessTime(Timestamp successTime) {
        this.successTime = successTime;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
