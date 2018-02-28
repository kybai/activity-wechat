package com.activity.service.impl;

import com.activity.mapper.UploadFileMapper;
import com.activity.model.UploadFile;
import com.activity.service.UploadFileService;
import com.activity.utils.DateUtils;
import com.activity.utils.FileUtil;
import com.activity.utils.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Created by ky.bai on 2018-02-19
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    @Transactional
    public UploadFile insert(MultipartFile file, String fileType) {
        UploadFile info = new UploadFile();
        String fileName = file.getOriginalFilename();
        String realName = FileUtil.makeFileNameDigital(fileName);
        try {
            OSSClientUtil.putObject(realName, file.getBytes(), file.getContentType());
            info.setFileName(fileName);
            info.setRealName(realName);
            info.setFilePath(OSSClientUtil.getObjectUrl(realName));
            info.setFileType(fileType);
            info.setCreateDate(DateUtils.getCurrentTimestamp());
            uploadFileMapper.insert(info);
        } catch (IOException e) {
            this.logger.error("文件上传失败；" + e.getMessage());
        }
        return info;
    }

    @Override
    public UploadFile selectOne(Integer id) {
        return uploadFileMapper.selectByPrimaryKey(id);
    }
}
