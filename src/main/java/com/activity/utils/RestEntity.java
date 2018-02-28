package com.activity.utils;

import java.io.Serializable;

/**
 * 接口返回值工具类
 *
 * @author Created by ky.bai on 2018-02-12
 */
public class RestEntity implements Serializable {
    private static final long serialVersionUID = -1392137781829143696L;

    private Integer status;
    private String msg;
    private Object data;

    public RestEntity() {
    }

    public RestEntity(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
