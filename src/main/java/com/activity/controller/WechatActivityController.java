package com.activity.controller;

import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityDescription;
import com.activity.model.ActivityDistrict;
import com.activity.model.ActivityEnroll;
import com.activity.model.ActivityTag;
import com.activity.model.ActivityThumbup;
import com.activity.model.ActivityWatched;
import com.activity.model.UploadFile;
import com.activity.model.WechatUser;
import com.activity.pojo.WechatPojo;
import com.activity.service.ActivityDistrictService;
import com.activity.service.ActivityEnrollService;
import com.activity.service.ActivityService;
import com.activity.service.ActivityThumbupService;
import com.activity.service.ActivityWatchedService;
import com.activity.service.UploadFileService;
import com.activity.service.UsersService;
import com.activity.service.WechatUserService;
import com.activity.utils.Constants;
import com.activity.utils.DateUtils;
import com.activity.utils.RestEntity;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Create by ky.bai on 2018-03-01 8:54
 */
@Controller
@RequestMapping("/wechat/activity")
public class WechatActivityController {

    @Autowired
    private ActivityDistrictService activityDistrictService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ActivityWatchedService activityWatchedService;

    @Autowired
    private ActivityThumbupService activityThumbupService;

    @Autowired
    private ActivityEnrollService activityEnrollService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private WxMpService wxMpService;

    /**
     * Created by ky.bai on 2018-03-01 14:49
     *
     * @param code 微信VIEW按钮对应code, 微信oauth2回调时生成并传递(须配置微信授权页面)
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) String code, @RequestParam(required = false) String openid, Model model) throws WxErrorException {
        if (StringUtils.isEmpty(openid)) {
            WxMpOAuth2AccessToken auth = wxMpService.oauth2getAccessToken(code);
            openid = auth.getOpenId();
        }
        model.addAttribute("openid", openid);
        model.addAttribute("districts", activityDistrictService.selectList(new ActivityDistrict(Boolean.TRUE)));
        //未结束活动的封面图
        model.addAttribute("fileList", activityService.selectWechatList(new WechatPojo(Boolean.TRUE)));
        model.addAttribute("mylist", activityService.selectUserWechatList(new WechatPojo(openid, Boolean.TRUE)));
        //我的信息与积分
        WechatUser wechatUser = wechatUserService.findByOpenid(openid);
        model.addAttribute("user", usersService.selectUserScore(wechatUser.getUserId()));
        return "wechat/list";
    }

    /**
     * Created by ky.bai on 2018-03-01 14:48
     *
     * @param activityId 活动编号
     * @param openid     用户微信openid
     */
    @RequestMapping(value = "/info/{activityId}", method = RequestMethod.GET)
    public String getInfo(@PathVariable Integer activityId, @RequestParam String openid, Model model) {
        Activity activity = activityService.selectOne(activityId);
        if (activity == null) {
            return "wechat/detail";
        }
        model.addAttribute("openid", openid);
        WechatUser user = wechatUserService.findByOpenid(openid);
        //活动与活动简介
        model.addAttribute("activity", activity);
        model.addAttribute("desc", activityService.selectDesc(new ActivityDescription(activityId)));
        //活动查看人数
        List<ActivityWatched> watchedList = activityWatchedService.selectList(new ActivityWatched(activityId));
        model.addAttribute("watchedTotal", (watchedList == null) ? 0 : watchedList.size());
        //活动点赞人数
        List<ActivityThumbup> thumbupList = activityThumbupService.selectList(new ActivityThumbup(activityId));
        model.addAttribute("thumbupTotal", (thumbupList == null ? 0 : thumbupList.size()));
        //我是否点赞
        List<ActivityThumbup> myThumbupList = activityThumbupService.selectList(new ActivityThumbup(activityId, user.getUserId()));
        model.addAttribute("isThumbup", !ObjectUtils.isEmpty(myThumbupList));
        //活动已报名人数
        List<ActivityEnroll> enrollList = activityEnrollService.selectList(new ActivityEnroll(activityId, Boolean.TRUE));
        int enrollTotal = (enrollList == null) ? 0 : enrollList.size();
        model.addAttribute("enrollTotal", enrollTotal);
        //活动状态
        model.addAttribute("activityStatus", getActivityStatus(activity, user.getUserId(), enrollTotal));
        //该活动添加一条查看记录
        activityWatchedService.insert(new ActivityWatched(activityId, user.getUserId()));
        return "wechat/detail";
    }

    /**
     * Created by ky.bai on 2018-03-01 11:30
     *
     * @param pojo 微信端请求参数
     *
     * @return 微信端活动列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getActivityList(@RequestBody WechatPojo pojo) {
        Map<String, Object> results = new HashMap<>();
        results.put("wechatList", activityService.selectWechatList(pojo));
        results.put("reviewList", activityService.selectWechatReviewList(pojo));
        results.put("myList", activityService.selectUserWechatList(pojo));
        return ResponseEntity.ok(new RestEntity(200, Constants.LOAD_SUCCESS, results));
    }

    /**
     * Created by ky.bai on 2018-03-02 09:26
     *
     * @param pojo 活动编号, 用户微信openid
     *
     * @return 活动点赞记录
     */
    @RequestMapping(value = "/thumbup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity thumbup(@RequestBody WechatPojo pojo) {
        String openid = pojo.getOpenid();
        Integer activityId = pojo.getActivityId();
        WechatUser user = wechatUserService.findByOpenid(openid);
        List<ActivityThumbup> thumbupList = activityThumbupService.selectList(new ActivityThumbup(activityId, user.getUserId()));
        if (!ObjectUtils.isEmpty(thumbupList)) {
            ActivityThumbup thumbup = thumbupList.get(0);
            activityThumbupService.delete(thumbup);
        } else {
            activityThumbupService.insert(new ActivityThumbup(activityId, user.getUserId()));
        }

        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    /**
     * Created by ky.bai on 2018-03-02 13:08
     *
     * @param activityId 活动编号
     * @param openid     微信用户openid
     *
     * @return 活动报名页
     */
    @RequestMapping(value = "/enroll/{activityId}", method = RequestMethod.GET)
    public String activityEnroll(@PathVariable Integer activityId, @RequestParam String openid, Model model) {
        model.addAttribute("openid", openid);
        WechatUser user = wechatUserService.findByOpenid(openid);
        model.addAttribute("userId", user.getUserId());
        Activity activity = activityService.selectOne(activityId);
        model.addAttribute("activity", activity);
        model.addAttribute("courses", activityService.selectCourseList(new ActivityCourse(activityId, Boolean.TRUE)));
        model.addAttribute("tag", activityService.selectTag(new ActivityTag(activityId)));
        return "wechat/signUp";
    }

    /**
     * Created by ky.bai on 2018-03-02 13:56
     *
     * @param record 报名信息
     *
     * @return 活动报名结果
     */
    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveEnroll(@RequestBody ActivityEnroll record) {
        Activity activity = activityService.selectOne(record.getActivityId());
        if (activity == null) {
            return ResponseEntity.ok(new RestEntity(100, Constants.OPERATOR_FAILED, Constants.ENROLL_RESULT_NOT_FOUND));
        }
        //判断当前活动是否在开始与结束时间范围内
        long currentTime = DateUtils.getCurrentTimestamp().getTime();
        if (activity.getBeginTime().getTime() > currentTime) {
            return ResponseEntity.ok(new RestEntity(100, Constants.OPERATOR_FAILED, Constants.ENROLL_RESULT_NOT_BEGIN));
        }
        if (activity.getEndTime().getTime() < currentTime) {
            return ResponseEntity.ok(new RestEntity(100, Constants.OPERATOR_FAILED, Constants.ENROLL_RESULT_WAS_END));
        }
        //判断是否已经报名
        List<ActivityEnroll> enrolls = activityEnrollService.selectList(new ActivityEnroll(activity.getId(), record.getUserId(), Boolean.TRUE));
        if (!ObjectUtils.isEmpty(enrolls)) {
            return ResponseEntity.ok(new RestEntity(100, Constants.OPERATOR_FAILED, Constants.ENROLL_RESULT_WAS_SIGN));
        }
        //判断已报名人数是否已满
        List<ActivityEnroll> enrollList = activityEnrollService.selectList(new ActivityEnroll(activity.getId(), Boolean.TRUE));
        if (activity.getMaxLimit() != null && activity.getMaxLimit() != 0 && activity.getMaxLimit() <= enrollList.size()) {
            return ResponseEntity.ok(new RestEntity(100, Constants.OPERATOR_FAILED, Constants.ENROLL_RESULT_WAS_FULL));
        }

        activityEnrollService.insert(record);
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, Boolean.TRUE));
    }

    /**
     * Created by ky.bai on 2018-03-02 13:55
     *
     * @param file 上传文件
     *
     * @return 上传文件编号
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity upload(@RequestParam(value = "uploadFile") MultipartFile file) {
        UploadFile info = uploadFileService.insert(file, "ENROLL");
        return ResponseEntity.ok(new RestEntity(200, Constants.OPERATOR_SUCCESS, info.getId()));
    }

    @RequestMapping(value = "/enroll/{activityId}/success", method = RequestMethod.GET)
    public String enterSuccess(@PathVariable Integer activityId, @RequestParam String openid, Model model) {
        model.addAttribute("activityId", activityId);
        model.addAttribute("openid", openid);
        return "wechat/enterSuccess";
    }

    /**
     * Created by ky.bai on 2018-03-02 17:03
     *
     * @param activity    活动
     * @param userId      用户编号
     * @param enrollTotal 活动已报名人数
     *
     * @return 活动状态: --未开始，--我要报名，--已报名，--名额已满，--已结束
     */
    private String getActivityStatus(Activity activity, Integer userId, int enrollTotal) {
        String status = Constants.ENROLL_NON_BEGIN;
        List<ActivityEnroll> enrolls = activityEnrollService.selectList(new ActivityEnroll(activity.getId(), userId, Boolean.TRUE));
        long currentTime = DateUtils.getCurrentTimestamp().getTime();
        if (!ObjectUtils.isEmpty(enrolls)) {
            status = Constants.ENROLL_SIGN;
        } else if (activity.getEndTime().getTime() <= currentTime) {
            status = Constants.ENROLL_END;
        } else if (activity.getMaxLimit() != null && activity.getMaxLimit() != 0 && activity.getMaxLimit() <= enrollTotal) {
            status = Constants.ENROLL_FULL;
        } else if (activity.getBeginTime().getTime() >= currentTime) {
            status = Constants.ENROLL_BEGIN;
        }

        return status;
    }
}
