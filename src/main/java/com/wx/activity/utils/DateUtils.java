package com.wx.activity.utils;

import java.util.Date;

/**
 * @author Create by ky.bai on 2018-02-11 11:26
 */
public class DateUtils {

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getDateByLong(Long timestamp) {
        return new Date(timestamp);
    }
}
