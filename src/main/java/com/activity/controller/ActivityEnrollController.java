package com.activity.controller;

import com.activity.model.Activity;
import com.activity.model.ActivityEnroll;
import com.activity.model.ActivityTag;
import com.activity.pojo.BasePageList;
import com.activity.service.ActivityEnrollService;
import com.activity.service.ActivityService;
import com.activity.utils.Constants;
import com.activity.utils.ExcelUtil;
import com.activity.utils.RestEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by ky.bai on 2018-02-27
 */
@Controller
@RequestMapping("/enroll")
public class ActivityEnrollController {

    @Autowired
    private ActivityEnrollService activityEnrollService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listHtml(@RequestParam Integer activityId, Model model) {
        model.addAttribute("activity", activityService.selectOne(activityId));
        model.addAttribute("tag", activityService.selectTag(new ActivityTag(activityId)));
        return "activity/enroll/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity list(@RequestBody BasePageList page) {
        //otherId充当活动编号
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        PageInfo<ActivityEnroll> info = new PageInfo<>(activityEnrollService.selectList(new ActivityEnroll(page.getOtherId(), Boolean.TRUE, page.getName())));
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, info));
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getInfo(@PathVariable Integer id) {
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, activityEnrollService.selectById(id)));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody ActivityEnroll record) {
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, activityEnrollService.insert(record)));
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateStatus(@PathVariable Integer id) {
        ActivityEnroll record = activityEnrollService.selectById(id);
        if (record != null) {
            record.setActive(!record.getActive());
            activityEnrollService.update(record);
        }
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    @RequestMapping(value = "/export/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> export(@PathVariable Integer activityId) throws UnsupportedEncodingException {
        Activity activity = activityService.selectOne(activityId);
        ActivityTag tag = activityService.selectTag(new ActivityTag(activityId));
        List<ActivityEnroll> list = activityEnrollService.selectList(new ActivityEnroll(activityId, Boolean.TRUE));

        //构造excel需要的数据结构
        String sheetTitle = activity.getTitle();
        Map<String, String> titleMap = new LinkedHashMap<>();
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        //构造标题
        if (tag.getUseName()) titleMap.put("name_4000", "姓名");
        if (tag.getUseSex()) titleMap.put("sex_2000", "性别");
        if (tag.getUsePhone()) titleMap.put("phone_4000", "联系方式");
        if (tag.getUsePolitical()) titleMap.put("political_4000", "政治面貌");
        if (tag.getUseCompany()) titleMap.put("company_8000", "工作单位");
        if (tag.getUseJob()) titleMap.put("job_6000", "职务");
        if (tag.getUseProfile()) titleMap.put("profile_12000", "个人简介");

        //构造数据
        if (!ObjectUtils.isEmpty(list)) {
            for (ActivityEnroll enroll : list) {
                Map<String, Object> map = new LinkedHashMap<>();
                if (tag.getUseName()) map.put("name", enroll.getName());
                if (tag.getUseSex()) map.put("sex", enroll.getSex());
                if (tag.getUsePhone()) map.put("phone", enroll.getPhone());
                if (tag.getUsePolitical()) map.put("political", enroll.getPolitical());
                if (tag.getUseCompany()) map.put("company", enroll.getCompany());
                if (tag.getUseJob()) map.put("job", enroll.getJob());
                if (tag.getUseProfile()) map.put("profile", enroll.getProfile());
                dataMapList.add(map);
            }
        }

        HttpHeaders httpReaders = new HttpHeaders();
        /*
         * ContentType:指定响应的 HTTP内容类型,如果未指定 ContentType，默认为TEXT/HTML
         * APPLICATION_OCTET_STREAM:任意的二进制类型
         */
        httpReaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        /*
         * ContentDisposition: 当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
         * ContentDispositionFormData:在响应类型为application/octet-stream情况下使用了这个头信息的话，那就意味着你不想直接显示内容，而是弹出一个”文件下载”的对话框
         */
        httpReaders.setContentDispositionFormData("attachment", new String(sheetTitle.getBytes(), Constants.CHARTSET_NAME_ISO) + ".xls");
        return new ResponseEntity<>(ExcelUtil.create(sheetTitle, titleMap, dataMapList), httpReaders, HttpStatus.OK);
    }

}
