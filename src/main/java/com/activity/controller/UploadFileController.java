package com.activity.controller;

import com.activity.model.UploadFile;
import com.activity.service.UploadFileService;
import com.activity.utils.Constants;
import com.activity.utils.FileUtil;
import com.activity.utils.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author Created by ky.bai on 2018-02-19
 */
@Controller
@RequestMapping("/file")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity upload(@RequestParam(value = "uploadFile") MultipartFile file,
                                 @RequestParam(value = "fileType") String fileType) {
        UploadFile info = uploadFileService.insert(file, fileType);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, info.getId()));
    }

    /**
     * Created by ky.bai on 2018/2/19 21:19
     *
     * @param id 下载文件的id
     */
    @RequestMapping(value = "/download/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String download(@PathVariable Integer id) {
        UploadFile info = uploadFileService.selectOne(id);
        return info.getFilePath();
    }

}
