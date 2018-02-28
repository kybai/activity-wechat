package com.activity.utils;

import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Random;

/**
 * 文件工具类
 * Created by ky.bai on 2018-02-28 14:28
 */
public class FileUtil {

    /**
     * 随机生成数字文件名
     *
     * @param fileName 文件名称
     */
    public static String makeFileNameDigital(String fileName) {
        long time = new Date().getTime();
        int randomInt = new Random().nextInt(1000);
        String withoutExtension = StringUtils.stripFilenameExtension(fileName);
        String extension = StringUtils.getFilenameExtension(fileName);
        StringBuilder sb = new StringBuilder();
        sb.append(time).append(randomInt);
        if (!StringUtils.isEmpty(withoutExtension) && !StringUtils.isEmpty(extension)) sb.append(".").append(extension);

        return sb.toString();
    }

}
