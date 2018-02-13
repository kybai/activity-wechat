package com.activity.pojo;

import java.io.Serializable;

/**
 * 禁用或失效数据的基础对象
 *
 * @author Created by ky.bai on 2018-02-12
 */
public class BaseDisabled implements Serializable {
    private static final long serialVersionUID = -4426265446119759078L;

    private Integer id;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
