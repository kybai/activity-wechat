package com.activity.controller;

import com.activity.model.UploadFile;
import com.activity.service.UploadFileService;
import com.activity.utils.Constants;
import com.activity.utils.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UEditor富文本编辑器
 *
 * @author Create by ky.bai on 2018-02-25 9:57
 */
@Controller
@RequestMapping("/ueditor")
public class UEditorController {

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ResponseBody
    public String config(HttpServletRequest request, HttpServletResponse response) {
        return "{\n"+
                "            \"imageActionName\": \"uploadimage\",\n" +
                "            \"imageFieldName\": \"file\", \n" +
                "            \"imageMaxSize\": 2048000, \n" +
                "            \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "            \"imageCompressEnable\": true, \n" +
                "            \"imageCompressBorder\": 1600, \n" +
                "            \"imageInsertAlign\": \"none\", \n" +
                "            \"imageUrlPrefix\": \"\" }";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) {
        UploadFile info = uploadFileService.insert(file, "UEDITOR");
        return "{\"state\": \"SUCCESS\"," +
                "\"url\": \"" + "/activity/file/download/" + info.getId() + "\"," +
                "\"title\": \"" + info.getRealName() + "\"," +
                "\"original\": \"" + info.getRealName() + "\"}";
    }
}
