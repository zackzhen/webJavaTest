package com.example.demo.handler;

import com.example.demo.bean.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @ClassName : GlobalExceptionHandler
 * @Description : 全局异常处理器
 * @author zzh
 * @create 2019/4/6
 * @version V1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Description: 处理包括基本异常和非验证参数异常
     * @Param: [e]
     * @Author: zzh
     * @Date: 11:22 2019/4/6
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        logger.error(getmessage(e));
        ResultDto result = new ResultDto();
        if (e instanceof BaseException) {
            BaseException exception = (BaseException) e;
            result.setCode(exception.getCode().toString());
            result.setMsg(exception.getMsg());
        } else {
            result.setCode("-2");
            result.setMsg("系统错误");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @Description: 处理@Valid参数验证异常
     * @Param: [e]
     * @Author: zzh
     * @Date: 11:24 2019/4/6
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ResultDto result = new ResultDto();
        result.setCode("-1");
        result.setMsg("参数错误");
        List<String> errorList = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                Map<String, String> errors = new HashMap<>();
                //同字段校验错误，覆盖上一个
                // fieldErrors.forEach(key -> errors.put(key.getField(), key.getDefaultMessage()));
                fieldErrors.forEach(key -> errorList.add(key.getField() + key.getDefaultMessage()));
                /*
                Map<String, String> errors = fieldErrors
                        .stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage));
                                */
                result.setData(errors);
                result.setMsg(String.join(",", errorList));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private String getmessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException esw) {
                    esw.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}