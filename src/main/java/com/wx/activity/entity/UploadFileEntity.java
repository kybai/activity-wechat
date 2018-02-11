package com.wx.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件类
 *
 * @author Create by ky.bai on 2018-02-10 20:58
 */
@Entity
@Table(name = "upload_file")
public class UploadFileEntity implements Serializable {
    private static final long serialVersionUID = -8762078549541426314L;

    private Integer id;
    //文件名称
    private String fileName;
    //文件存放名称
    private String realName;
    //文件路径
    private String filePath;
    //文件类型
    private String fileType;
    //创建日期
    private Date createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
