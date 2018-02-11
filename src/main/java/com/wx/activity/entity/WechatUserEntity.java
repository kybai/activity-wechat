package com.wx.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 微信公众号用户
 *
 * @author Create by ky.bai on 2018-02-10 21:08
 */
@Entity
@Table(name = "wechat_user")
public class WechatUserEntity implements Serializable {
    private static final long serialVersionUID = -900539574353010912L;

    //用户唯一标识
    private String openid;
    //用户昵称
    private String nickname;
    //关注状态, 0-未关注,获取不到用户的信息
    private Boolean subscribe;
    //用户性别, 1-男性  2-女性  0-未知
    private Integer sex;
    //城市
    private String city;
    //省份
    private String province;
    //国家
    private String country;
    //用户头像
    private String headimgurl;
    //用户关注时间
    private Date subscribeTime;
    //用户关联编号
    private String unionid;
    //备注
    private String remark;
    //分组编号
    private Integer groupid;
    //有效性
    private Boolean active;
    //创建日期
    private Date createDate;

    public WechatUserEntity() {
    }

    public WechatUserEntity(String openid, String nickname, Boolean subscribe, Integer sex, String city, String province, String country, String headimgurl, Date subscribeTime, String unionid,
                            String remark, Integer groupid, Boolean active, Date createDate) {
        this.openid = openid;
        this.nickname = nickname;
        this.subscribe = subscribe;
        this.sex = sex;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
        this.subscribeTime = subscribeTime;
        this.unionid = unionid;
        this.remark = remark;
        this.groupid = groupid;
        this.active = active;
        this.createDate = createDate;
    }

    @Id
    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @Column(name = "subscribe")
    public Boolean getSubscribe() {
        return subscribe;
    }

    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    @Column(name = "headimgurl")
    public String getHeadimgurl() {
        return headimgurl;
    }

    @Column(name = "subscribe_time")
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    @Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "groupid")
    public Integer getGroupid() {
        return groupid;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
