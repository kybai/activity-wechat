package com.activity.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Create by ky.bai on 2018-02-11 11:26
 */
public class DateUtils {

    public static final String formatDay = "yyyy-MM-dd";
    public static final String formatMin = "yyyy-MM-dd HH:mm";
    public static final String formatSec = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    public static String getByTimestamp(Timestamp time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time.getTime()));
    }
}
