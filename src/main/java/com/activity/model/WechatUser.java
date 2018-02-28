package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "wechat_user")
public class WechatUser implements Serializable {
    private static final long serialVersionUID = -6651780190553926909L;

    private Integer id;
    //微信标识
    private String openid;
    //用户编号
    private Integer userId;
    //用户昵称
    private String nickname;
    //用户关注
    private Boolean subscribe;
    //用户关联编号
    private String unionId;
    //备注
    private String remark;
    //分组编号
    private Integer groupId;
    //创建时间
    private Timestamp createDate;

    public WechatUser() {
    }

    public WechatUser(String openid, Integer userId, String nickname, Boolean subscribe, String unionId, String remark, Integer groupId, Timestamp createDate) {
        this.openid = openid;
        this.userId = userId;
        this.nickname = nickname;
        this.subscribe = subscribe;
        this.unionId = unionId;
        this.remark = remark;
        this.groupId = groupId;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @Column(name = "subscribe")
    public Boolean getSubscribe() {
        return subscribe;
    }

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}