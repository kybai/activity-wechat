package com.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 轮播图
 *
 * @author Create by ky.bai on 2018-02-24 14:11
 */
@Entity
@Table(name = "rolling_image")
public class RollingImage {

    private Integer id;
    //名称
    private String name;
    //图片对应文件
    private Integer uploadFileId;
    //点击跳转路径
    private String url;
    //有效性
    private Boolean active;
    //创建时间
    private Timestamp createDate;

    public RollingImage() {
    }

    public RollingImage(Boolean active) {
        this.active = active;
    }

    public RollingImage(String name, Boolean active) {
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

    @Column(name = "upload_file_id")
    public Integer getUploadFileId() {
        return uploadFileId;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUploadFileId(Integer uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollingImage that = (RollingImage) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(uploadFileId, that.uploadFileId) && Objects.equals(url, that.url) && Objects.equals(active, that
                .active) && Objects.equals(createDate, that.createDate);
    }

    public int hashCode() {
        return Objects.hash(id, name, uploadFileId, url, active, createDate);
    }
}
