package com.example.demo.handler;

import lombok.Data;

/*
 * @ClassName : BaseException
 * @Description : 自定义基础异常
 * @author zzh
 * @create 2019/4/6
 * @version V1.0
 */
@Data
public class BaseException extends RuntimeException {
    public Integer code;
    public String msg;
    public BaseException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}