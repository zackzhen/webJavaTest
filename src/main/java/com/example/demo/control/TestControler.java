package com.example.demo.control;

import com.example.demo.util.ApiTools;
import com.example.demo.handler.BaseException;
import com.example.demo.util.QiniuUploadUtil;
import com.example.demo.entity.ResAccCode;
import com.example.demo.entity.UserDo;
import com.example.demo.service.UserService;
import com.qiniu.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Slf4j
@RestController
@Api(value = "xxx模块描述")
@RequestMapping("/main")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {}, allowCredentials = "true")
public class TestControler {

    //    @Autowired
//    private DemoConfig configs;
    @Autowired
    private ApiTools apiTools;
    @Autowired
    private QiniuUploadUtil uploadUtil;

    @Autowired
    private UserService userService;

    @ResAccCode
    @ApiOperation(value = "查询系统用户", notes = "列表", httpMethod = "GET")
    @GetMapping("/querySysUser")
    public ResponseEntity querySysUser() {

        List<UserDo> userDos = userService.findAll();

        return new ResponseEntity(apiTools.setResult(Json.encode(userDos)), HttpStatus.OK);
    }

    @ResAccCode
    @ApiOperation(value = "通过名字查询系统用户", notes = "列表", httpMethod = "GET")
    @GetMapping("/querySysUserByName")
    public ResponseEntity querySysUserByName(@ApiParam(value = "用户名，支持模糊", required = true) String name) {

        List<UserDo> userDos = userService.queryByName(name);

        return new ResponseEntity(apiTools.setResult(Json.encode(userDos)), HttpStatus.OK);
    }



    @ResAccCode
    @ApiOperation(value = "增加系统用户", notes = "列表", httpMethod = "POST")
    @PostMapping("/saveSysUser")
    public ResponseEntity saveSysUser() {

        List<UserDo> userDos = userService.findAll();

        return new ResponseEntity(apiTools.setResult(Json.encode(userDos)), HttpStatus.OK);
    }

    @ResAccCode
    /* 方法注解 */
    @ApiOperation(value = "图片上传", notes = "七牛云图片上传", httpMethod = "POST")
    @PostMapping("/upload")
    public ResponseEntity uploadImg(@ApiParam(value = "参数描述", required = true) MultipartFile file) {
        try {
            return new ResponseEntity(apiTools.setResult(uploadUtil.createThumbnail(file, 2500, 2500)), HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BaseException(1, "【七牛云存储】上传失败，返回信息message=" + e.getMessage());
        }
    }
}
