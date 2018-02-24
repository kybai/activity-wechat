package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 广告图/轮播图
 *
 * @author Create by ky.bai on 2018-02-24 13:53
 */
@Entity
@Table(name = "adsense")
public class Adsense implements Serializable {
    private static final long serialVersionUID = -7116991943771923511L;

    private Integer id;
    //名称
    private String name;
    //广告图片编号
    private Integer uploadFileId;
    //广告跳转链接
    private String url;
    //对应页面
    private String pageName;
    //图片类型：广告图、轮播图
    private String type;
    //有效性
    private Boolean active;
    //创建日期
    private Timestamp createDate;

    public Adsense() {
    }

    public Adsense(String type) {
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "upload_file_id")
    public Integer getUploadFileId() {
        return uploadFileId;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "page_name")
    public String getPageName() {
        return pageName;
    }

    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUploadFileId(Integer uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adsense adsense = (Adsense) o;
        return Objects.equals(id, adsense.id) && Objects.equals(name, adsense.name) && Objects.equals(uploadFileId, adsense.uploadFileId) && Objects.equals(url, adsense.url) && Objects.equals
                (pageName, adsense.pageName) && Objects.equals(type, adsense.type) && Objects.equals(active, adsense.active) && Objects.equals(createDate, adsense.createDate);
    }

    public int hashCode() {

        return Objects.hash(id, name, uploadFileId, url, pageName, type, active, createDate);
    }
}
