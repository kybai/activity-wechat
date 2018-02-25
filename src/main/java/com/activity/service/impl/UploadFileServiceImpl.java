package com.activity.service.impl;

import com.activity.mapper.UploadFileMapper;
import com.activity.model.UploadFile;
import com.activity.service.UploadFileService;
import com.activity.utils.DateUtils;
import com.activity.utils.FileUtil;
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

    @Value("${file.upload.path}")
    private String path;

    @Override
    @Transactional
    public UploadFile insert(MultipartFile file, String fileType) {
        UploadFile info = new UploadFile();
        String fileName = file.getName();
        String realName = FileUtil.makeFileNameDigital(fileName);
        String filePath = path + File.separator;
        try {
            if (FileUtil.upload(file.getBytes(), filePath, realName)) {
                info.setFileName(fileName);
                info.setRealName(realName);
                info.setFilePath(filePath);
                info.setFileType(fileType);
                info.setCreateDate(DateUtils.getCurrentTimestamp());
                uploadFileMapper.insert(info);
            }
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
