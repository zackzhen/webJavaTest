package com.example.demo.util;

import com.example.demo.config.DemoConfig;
import com.example.demo.handler.BaseException;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * @ClassName : QiniuUploadUtil
 * @Description : 七牛云存储上传工具
 * @author zzh
 * @create 2019/5/12
 * @version V1.0
 */
@Slf4j
@Component
public class QiniuUploadUtil {

    @Autowired
    private DemoConfig configs;



    @Autowired
    private ApiTools apiTools;

    public void uploadFile() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        //文件路径
        String uploadFilePath = "E:\\小超人朋友圈背景13.jpg";

        String key = null;

        Auth auth = Auth.create(configs.accesskey, configs.secretkey);
        String upToken = auth.uploadToken(configs.bucket);
        try {
            Response response = uploadManager.put(uploadFilePath, key, upToken);
            //解析成功上传结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("图片上传地址={}", configs.ossUrl + putRet.key);
            //log.info("【putRet】上传结果hash={}", putRet.hash);
        } catch (QiniuException e) {
            log.error(e.getMessage());
            throw new BaseException(1, "【七牛云存储】上传失败，返回信息message=" + e.getMessage());
        }
    }

    /**
     * @Description: 文件压缩
     * @Param:
     * @Author: zzh
     * @Date: 22:03 2019/5/13
     */

    public String createThumbnail(MultipartFile file, float width,
                                  float height) throws IOException {
        if (file == null || file.isEmpty()) {
            log.error("文件不存在");
            return null;
        }

        ByteArrayOutputStream bos = null;
            /*
        压缩文件
         */
        BufferedImage image = ImageIO.read(file.getInputStream());
        // 获得缩放的比例
        double ratio = 1.0;
        // 判断如果高、宽都不大于设定值，则不处理
        if (image.getHeight() > height || image.getWidth() > width) {
            if (image.getHeight() > image.getWidth()) {
                ratio = height / image.getHeight();
            } else {
                ratio = width / image.getWidth();
            }
        }
        // 计算新的图面宽度和高度
        int newWidth = (int) (image.getWidth() * ratio);
        int newHeight = (int) (image.getHeight() * ratio);

        BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_RGB);
        bfImage.getGraphics().drawImage(
                image.getScaledInstance(newWidth, newHeight,
                        BufferedImage.SCALE_SMOOTH), 0, 0, Color.WHITE, null);
        bos = new ByteArrayOutputStream();
        ImageIO.write(bfImage, "jpg", bos);
        /*
        调用七牛云上传
         */
        String key = null;
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(configs.accesskey, configs.secretkey);
        String upToken = auth.uploadToken(configs.bucket);
        Response response = uploadManager.put(bos.toByteArray(), key, upToken);
        //解析成功上传结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("图片上传地址={}", configs.ossUrl + putRet.key);
        //关闭流
        bos.close();
        return configs.ossUrl + putRet.key;
    }

    /**
     * @Description: 根据RenderedImage上传文件
     * @Param: [data]
     */
    public String uploadImage(RenderedImage im) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String key = null;
        DefaultPutRet putRet = null;
        try {
            ImageIO.write(im, "jpg", bos);
            Configuration cfg = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(configs.accesskey, configs.secretkey);
            String upToken = auth.uploadToken(configs.bucket);
            Response response = uploadManager.put(bos.toByteArray(), key, upToken);
            //解析成功上传结果
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            bos.close();
        } catch (IOException e) {
            log.error("上传文件异常={}", e.getMessage());
        }
        return configs.ossUrl + putRet.key;
    }


    /**
     * 添加GIF上传功能
     * @param file
     * @return
     * @throws IOException
     */
    public String createGifImage(MultipartFile file) throws IOException {
        String sourcePath = "uploadImg/" + apiTools.generateHtmlName() + ".gif";
        return saveFile(file, sourcePath);
    }


    public String createVideoMp4(MultipartFile file) throws IOException {
        String sourcePath = "uploadVideo/" + apiTools.generateHtmlName() + ".mp4";
        return saveFile(file, sourcePath);
    }

    private String saveFile(MultipartFile file, String sourcePath) throws IOException {
        if (file == null || file.isEmpty()) {
            log.error("文件不存在");
            return null;
        }

        InputStream inputStream = file.getInputStream();
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(configs.targetPath + sourcePath);
        while ((index = inputStream.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        inputStream.close();
        downloadFile.close();
        return configs.serverUrl + sourcePath;
    }
}