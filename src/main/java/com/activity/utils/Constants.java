package com.activity.utils;

/**
 * @author Create by ky.bai on 2018-02-11 11:30
 */
public class Constants {

    /*登录结果*/
    public static final String LOGIN_FAILURE_ERROR = "登录失败，帐号密码错误.";
    public static final String LOGIN_FAILURE_NOT_EMPTY = "登录失败，帐号密码不能为空.";
    public static final String LOGIN_FAILURE_DISABLED = "登录失败，该用户已被禁用.";
    public static final String LOGIN_FAILURE_NOT_EXIST = "登录失败，找不到该用户.";

    //操作提示
    public static final String OPERATOR_SUCCESS = "操作成功";
    public static final String LOAD_SUCCESS = "加载成功";

    public static final String CHARTSET_NAME_ISO = "ISO-8859-1";
    public static final String CHARTSET_NAME_UTF = "UTF-8";

    //积分数量
    public static final Integer SCORE_SIGN_COURSE = 1;
    public static final Integer SCORE_MISS_COURSE = -2;

    //活动详情报名按钮状态
    public static final String ENROLL_BEGIN = "我要报名";
    public static final String ENROLL_FULL = "名额已满";
    public static final String ENROLL_END = "已结束";
    public static final String ENROLL_SIGN = "已报名";

    //活动报名提示结果
    public static final String ENROLL_RESULT_WAS_DISABLED = "您的账号已被禁用，请联系管理员。";
    public static final String ENROLL_RESULT_NOT_FOUND = "报名失败，未发现该活动";
    public static final String ENROLL_RESULT_WAS_END = "报名失败，该活动已经结束";
    public static final String ENROLL_RESULT_WAS_FULL = "报名失败，该活动名额已满";
    public static final String ENROLL_RESULT_WAS_SIGN = "报名失败，该活动您已报名";

    //课程签到提示
    public static final String COURSE_SIGN_NOT_TIME = "签到失败，不在签到时间阶段";
    public static final String COURSE_SIGN_NOT_ENROLL = "签到失败，您没有报名该课程";
    public static final String COURSE_SIGN_WAS_SIGNED = "签到失败，您已签到该课程";
    public static final String COURSE_SIGN_SUCCESS = "签到成功";

    //图片类型
    public static final String IMAGE_TYPE_ADSENSE = "广告图";
    public static final String IMAGE_TYPE_ROLLING = "轮播图";

    //微信配置关键字
    public static final String WECHAT_CONFIG_APPID = "APPID";
    public static final String WECHAT_CONFIG_SECRET = "SECRET";
    public static final String WECHAT_CONFIG_TOKEN = "TOKEN";
    public static final String WECHAT_CONFIG_AESKEY = "AESKEY";
    public static final String WECHAT_CONFIG_URI = "URI";
    public static final String WECHAT_CONFIG_SUBSCRIBE_MSG = "SUBSCRIBE_MSG";
    public static final String WECHAT_CONFIG_AUTO_REPLY_MSG = "AUTO_REPLY_MSG";

    //微信路径state
    public static final String WECHAT_STATE_INDEX = "INDEX";
    public static final String WECHAT_STATE_INDEX_MY = "my";

}
