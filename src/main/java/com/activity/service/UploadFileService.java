package com.activity.service;

import com.activity.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Created by ky.bai on 2018-02-19
 */
public interface UploadFileService {

    UploadFile insert(MultipartFile file, String fileType);

    UploadFile selectOne(Integer id);

}
