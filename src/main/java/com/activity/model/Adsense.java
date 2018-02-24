package com.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * 广告图
 *
 * @author Create by ky.bai on 2018-02-24 13:53
 */
@Entity
@Table(name = "adsense")
public class Adsense implements Serializable {
    private static final long serialVersionUID = -7116991943771923511L;

    private Integer id;
    //广告名称
    private String name;
    //广告内容
    private String content;
    //广告跳转链接
    private String url;
    //对应页面
    private String pageName;
    //有效性
    private Boolean active;

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

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "page_name")
    public String getPageName() {
        return pageName;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adsense adsense = (Adsense) o;
        return Objects.equals(id, adsense.id) && Objects.equals(name, adsense.name) && Objects.equals(content, adsense.content) && Objects.equals(url, adsense.url) && Objects.equals(pageName,
                adsense.pageName) && Objects.equals(active, adsense.active);
    }

    public int hashCode() {
        return Objects.hash(id, name, content, url, pageName, active);
    }
}
