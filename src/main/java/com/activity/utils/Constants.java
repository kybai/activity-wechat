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

    //微信用户关注提示
    public static final String USER_SUBSCRIBE = "感谢关注";

    //分页数据列表的每页条数
    public static final int PAGE_SIZE = 15;

    public static final String CHARTSET_NAME_ISO = "ISO-8859-1";
    public static final String CHARTSET_NAME_UTF = "UTF-8";

    //积分数量
    public static final Integer SCORE_SIGN_COURSE = 1;
    public static final Integer SCORE_MISS_COURSE = -1;
}
