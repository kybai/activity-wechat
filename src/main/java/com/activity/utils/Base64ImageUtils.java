package com.activity.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wanghua on 17/3/9.
 */
public class Base64ImageUtils {
    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imgFilePath
     * @return 返回Base64编码过的字节数组字符串
     */
    public static String getImageBase64Str(String imgFilePath) {
        byte[] data = null;
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    /**
     * 将图片Base64字符串转换为字节流
     *
     * @param imageBase64Str 图片Base64字符串
     * @return
     */
    public static byte[] imageBase64StrToBytes(String imageBase64Str) {
        if (StringUtils.isBlank(imageBase64Str)) return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(imageBase64Str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imageBase64Str 图片Base64字符串
     * @param imgFilePath    图片输出地址
     * @return
     */
    public static boolean generateImage(String imageBase64Str, String imgFilePath) {
        if (StringUtils.isBlank(imageBase64Str)) return false;
        OutputStream out = null;
        try {
            byte[] bytes = imageBase64StrToBytes(imageBase64Str);
            for (int i = 0; i < bytes.length; ++i)
                if (bytes[i] < 0)
                    bytes[i] += 256;
            out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        // 测试从Base64编码转换为图片文件
        String base64Str = "xxxxx";
        generateImage(base64Str, "C:\\a1.jpg");

        // 测试从图片文件转换为Base64编码
        System.out.println(getImageBase64Str("C:\\a2.jpg"));
    }
}
