package com.activity.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by wanghua on 17/3/8.
 */
public class OSSClientUtil {
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName1;      //存储空间
    private static String filedir;          //文件存储目录
    private static OSSClient ossClient;     //线程安全的
    private static BucketInfo bucketInfo1;  //存储空间

    static {
        endpoint = "oss-cn-hangzhou.aliyuncs.com";
        accessKeyId = "LTAIUsNypUGkyTC3";
        accessKeySecret = "z8ler2QCQPW2ZPOM4dO4Zw88j8VQYU";
        bucketName1 = "shiyu-test";
        filedir = "data/";
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        bucketInfo1 = ossClient.getBucketInfo(bucketName1);
    }

    /**
     * Base64图片上传，如果同名文件会覆盖服务器上的
     *
     * @param key        请使用UploadFile实体ID
     * @param base64Str
     * @param fileSuffix
     * @return
     */
    public static PutObjectResult putBase64Img(String key, String base64Str, String fileSuffix) {
        int i = base64Str.indexOf(",");
        if (i != -1) base64Str = base64Str.substring(i + 1);
        byte[] bytes = Base64ImageUtils.imageBase64StrToBytes(base64Str);
        return putObject(key, bytes, fileSuffix);
    }

    /**
     * 对象上传，如果同名文件会覆盖服务器上的
     *
     * @param key        请使用UploadFile实体ID
     * @param bytes
     * @param fileSuffix
     * @return
     */
    public static PutObjectResult putObject(String key, byte[] bytes, String fileSuffix) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(getContentType(fileSuffix));
        //objectMetadata.setContentDisposition("inline;filename=" + fileName);
        return ossClient.putObject(bucketName1, key, new ByteArrayInputStream(bytes), objectMetadata);
    }

    public static PutObjectResult putObjectUrl(String key, String url, String fileSuffix) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(getContentType(fileSuffix));

        InputStream inputStream = new URL(url).openStream();
        return ossClient.putObject(bucketName1, key , inputStream,objectMetadata);
    }

    /**
     * Object是否存在
     *
     * @return
     */
    public static boolean doesObjectExist(String key) {
        return ossClient.doesObjectExist(bucketName1, key);
    }

    /**
     * 删除单个Object
     *
     * @return
     */
    public static void deleteObject(String key) {
        ossClient.deleteObject(bucketName1, key);
    }

    /**
     * 获取文件的相对路径
     *
     * @param fileName 文件名称，格式如：abc.png
     * @return 返回格式如：
     */
    public static String getObjectRelativePath(String fileName) {
        Bucket bucket = bucketInfo1.getBucket();
        String fileFullPath = MessageFormat.format("{0}.{1}/{2}", bucket.getName(), bucket.getExtranetEndpoint());
        return fileFullPath;
    }

    /**
     * 获得对象url链接（此方法不会向OSS服务器发送请求，使用频率高也无妨）
     *
     * @param key
     * @return
     */
    public static String getObjectUrl(String key) {
        if (org.apache.commons.lang3.StringUtils.isBlank(key)) return "";
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName1, key, expiration);

//        if (url != null)
//            return    url.toString();

        if (url != null){
            String sbustr = url.toString();
            //TODO 页面中会把&符号转义 amp; 所以截取图片地址，？只要问号前的。
            return sbustr.substring(0,sbustr.indexOf("?"));
        }

        return null;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        } else if (FilenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        } else if (FilenameExtension.equalsIgnoreCase("png")) {
            return "image/png";
        } else if (FilenameExtension.equalsIgnoreCase("jpe") ||
                FilenameExtension.equalsIgnoreCase("jpeg") ||
                FilenameExtension.equalsIgnoreCase("jpg") ||
                FilenameExtension.equalsIgnoreCase("jfif")) {
            return "image/jpeg";
        } else if (FilenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        } else if (FilenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        } else if (FilenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        } else if (FilenameExtension.equalsIgnoreCase("pptx") ||
                FilenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        } else if (FilenameExtension.equalsIgnoreCase("docx") ||
                FilenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        } else if (FilenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 销毁
     */
    public void destory() {
        if (ossClient != null)
            ossClient.shutdown();
    }

    public static void main(String[] args) throws IOException {
        String key = "354617";

        File file = new File("E:/aa.png");
        FileInputStream fis = new FileInputStream(file);

//        OSSClientUtil.putObject(key, StreamUtil.readBytesFromInputStream(fis), "jpg");
        OSSClientUtil.putObject("363801", StreamUtil.readBytesFromInputStream(fis), "jpg");
        System.out.println(OSSClientUtil.getObjectUrl("363801"));
        fis.close();
//·
//        System.out.println(OSSClientUtil.doesObjectExist(key));

//        System.out.println(OSSClientUtil.getObjectUrl(key));

//        OSSClientUtil.deleteObject(key);
    }
}
