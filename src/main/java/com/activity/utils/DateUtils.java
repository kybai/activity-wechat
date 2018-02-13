package com.activity.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Create by ky.bai on 2018-02-11 11:26
 */
public class DateUtils {

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

}
