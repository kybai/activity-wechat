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
    private Integer userID;
    //用户昵称
    private String nickname;
    //用户关注
    private Boolean subscribe;
    //用户关联编号
    private String unionID;
    //备注
    private String remark;
    //分组编号
    private Integer groupID;
    //创建时间
    private Timestamp createDate;

    public WechatUser() {
    }

    public WechatUser(String openid, Integer userID, String nickname, Boolean subscribe, String unionID, String remark, Integer groupID, Timestamp createDate) {
        this.openid = openid;
        this.userID = userID;
        this.nickname = nickname;
        this.subscribe = subscribe;
        this.unionID = unionID;
        this.remark = remark;
        this.groupID = groupID;
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
    public Integer getUserID() {
        return userID;
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
    public String getUnionID() {
        return unionID;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "group_id")
    public Integer getGroupID() {
        return groupID;
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

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}