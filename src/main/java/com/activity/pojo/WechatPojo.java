package com.activity.pojo;

/**
 * @author Create by ky.bai on 2018-03-01 11:44
 */
public class WechatPojo extends BasePage {
    private static final long serialVersionUID = -3487649199624739070L;

    //用户微信openid
    private String openid;
    //区域编号
    private Integer districtId;
    //有效性
    private Boolean active;

    public WechatPojo() {
    }

    public WechatPojo(Boolean active) {
        this.active = active;
    }

    public WechatPojo(String openid, Boolean active) {
        this.openid = openid;
        this.active = active;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
