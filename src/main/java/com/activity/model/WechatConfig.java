package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Created by ky.bai on 2018-03-04
 */
@Entity
@Table(name = "wechat_config")
public class WechatConfig implements Serializable {
    private static final long serialVersionUID = -3890418234532386327L;

    private Integer id;
    //配置关键字
    private String configKey;
    //对应值
    private String configText;
    //备注
    private String remark;
    //创建时间
    private Timestamp createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "config_key")
    public String getConfigKey() {
        return configKey;
    }

    @Column(name = "config_Text")
    public String getConfigText() {
        return configText;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public void setConfigText(String configText) {
        this.configText = configText;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
