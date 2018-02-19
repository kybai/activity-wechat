package com.activity.mapper;

import com.activity.model.UploadFile;

public interface UploadFileMapper {

    int insert(UploadFile record);

    UploadFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UploadFile record);
}