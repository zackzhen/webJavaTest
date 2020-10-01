package com.example.demo.util;

import com.example.demo.config.DemoConfig;
import com.example.demo.bean.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/*
 * @ClassName : ApiTools
 * @Description : TODO
 * @author zzh
 * @create 2019/4/5
 * @version V1.0
 */
@Component
public class ApiTools {
    @Autowired
    private DemoConfig configs;

    public String generateIdentifier() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String uuid = fmt.format(date) + new Double(Math.random() * 100000).intValue();
        while (uuid.length() < 22) {
            uuid = uuid + "0";
        }
        uuid = uuid.substring(2);
        return uuid;
    }

    public String generateHtmlName() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String name = fmt.format(date);
        return name;
    }

    public String generateRoomId() {
        //LocalDateTime date = LocalDateTime.now();
        //DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String roomId = new Double((Math.random() * 9 + 1) * 1000).intValue() + "";
        //+ fmt.format(date);
        // roomId = roomId.substring(2);
        return roomId;
    }

    /**
     * @Description: 生成随机6位下载码
     * @Param: []
     */
    public String generateRangeCode(){
        String code = new Double((Math.random() * 9 + 1) * 100000).intValue() + "";
        return code;
    }
    /**
     * @Description: 生成随机字符串
     * @Param: [length]
     */
    public String generateRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * @Description: 补充地址 
     * @Param: [url]
     */
    public String getUrl(String url) {
        if (ObjectUtil.isNotEmpty(url)) {
            return configs.serverUrl +"activity/" + url;
        }
        return null;
    }

    public String getSourceUrl(String url) {
        if (ObjectUtil.isNotEmpty(url)) {
            StringBuilder address = new StringBuilder(configs.serverUrl);
            return address.append("share").append("/").append(url).toString();
        }
        return null;
    }
    /**
     * @Description: 封装返回 
     * @Param: [object]
     */
    public ResultDto setResult(Object object) {
        ResultDto result = new ResultDto();
        result.setData(object);
        return result;
    }

    public static void main(String[] args) {
    }
}