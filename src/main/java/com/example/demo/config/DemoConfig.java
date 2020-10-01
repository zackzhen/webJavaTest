package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * @ClassName : PushTalkConfig
 * @Description : 配置bean类
 * @author zzh
 * @create 2019/5/17
 * @version V1.0
 */
@Component
public class DemoConfig {

    /*
     *  七牛云key
     */
    @Value("${qn.accesskey}")
    public String accesskey;

    /*
    * 七牛云secretkey
     */
    @Value("${qn.secretkey}")
    public String secretkey;

    /*
    * 七牛云bucket
     */
    @Value("${qn.bucket}")
    public String bucket;

    /*
    * 七牛云空间地址
     */
    @Value("${qn.ossUrl}")
    public String ossUrl;



    /*
    * 小米推送 miAppSecret
     */
    public String miAppSecret;

    /*
    * 小米推送 packagename
     */
    public String packageName;

    /*
    * 生成html目标文件夹
     */
    @Value("${server.targetPath}")
    public String targetPath;

    /*
    * 服务器地址
     */
    @Value("${server.serverUrl}")
    public String serverUrl;

    /*
    * api运行环境
     */
    public String apiEnv;

    /*
    * 微博 clientID
     */
    public String clientID;

    /*
    * 微博 clientSercret
     */
    public String clientSercret;

    /*
    * 微博重定向 redirectURI
     */
    public String redirectURI;

    /*
    * 微博 baseURL
     */
    public String shortURL;

    /*
    * 微博 accessTokenURL
     */
    public String accessTokenURL;

    /*
    * 微博 authorizeURL
     */
    public String authorizeURL;
     /*
    * 微博 rmURL
     */
    public String rmURL;

    public String emailHost;


    public String emailPost;

    public String username;

    public String password;

    /*
    * 好单库相关
     */
    public String haoDanKu;

    public String apiKey;

    /*
    资源文章生成目录
     */
    public String sourcePath;
}