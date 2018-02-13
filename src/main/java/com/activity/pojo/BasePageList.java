package com.activity.pojo;

import java.io.Serializable;

/**
 * 分页数据的基础对象
 *
 * @author Created by ky.bai on 2018-02-12
 */
public class BasePageList extends BasePage implements Serializable {
    private static final long serialVersionUID = 8753586475772434694L;
    //名称查询（亦充当编号）
    private String name;
    //有效性查询
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
