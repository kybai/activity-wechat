package com.activity.utils;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Random;

public class FileUtil {
    private final static Logger log = Logger.getLogger(FileUtil.class);

    /**
     * 上传文件到服务器
     *
     * @param data     文件的byte格式数据
     * @param filePath 保存路径
     * @param realName 文件名称
     * @return 是否上传成功
     */
    public static boolean upload(byte[] data, String filePath, String realName) {
        boolean isUpload = true;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileCopyUtils.copy(data, new FileOutputStream(file.getPath() + File.separator + realName));
        } catch (IOException e) {
            log.error("FileUtil.upload has error: " + e.getMessage());
            e.printStackTrace();
            isUpload = false;
        }
        return isUpload;
    }

    /**
     * 文件下载
     *
     * @param response 请求
     * @param file     文件
     * @param saveName 名称
     */
    public static void download(HttpServletResponse response, File file, String saveName) {
        if (ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(file))
            return;

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = response.getOutputStream();
            response.reset(); //清空输出流

            response.setHeader("Content-disposition", "attachment; filename=" + new String(saveName.getBytes("UTF-8"), "ISO-8859-1"));
            response.setContentType("image/png");
            response.setCharacterEncoding("UTF-8");

            writeFileStreamToOutput(fis, os);
        } catch (FileNotFoundException e) {
            log.error("download has file not found error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("download has io error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeFileStream(fis, os);
        }
    }

    private static void writeFileStreamToOutput(FileInputStream fis, OutputStream os) throws IOException {
        byte[] bytes = new byte[8 * 1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        os.close();
    }

    private static void closeFileStream(FileInputStream fis, OutputStream os) {
        try {
            if (!ObjectUtils.isEmpty(fis)) {
                fis.close();
            }
            if (!ObjectUtils.isEmpty(os)) {
                os.close();
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

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
        StringBuffer sb = new StringBuffer();
        sb.append(time).append(randomInt);
        if (!StringUtils.isEmpty(withoutExtension) && !StringUtils.isEmpty(extension))
            sb.append(".").append(extension);

        return sb.toString();
    }

}
