package com.activity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 上传文件
 * Created by ky.bai on 2018/2/19 20:20
 */
@Entity
@Table(name = "upload_file")
public class UploadFile implements Serializable {
    private static final long serialVersionUID = -4820676747689380625L;

    //文件编号
    private Integer id;
    //文件名称
    private String fileName;
    //存放名称
    private String realName;
    //文件路径
    private String filePath;
    //文件类型
    private String fileType;
    //上传时间
    private Timestamp createDate;

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
    public Timestamp getCreateDate() {
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

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}