package com.bobo.myspringsecurity.common.handler.exceptionhandler;


import com.bobo.myspringsecurity.common.utils.Result;
import com.bobo.myspringsecurity.common.utils.ResultCode;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;

/**
 * @author bobo
 * @createTime 2021/10/7
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行了全局异常");
    }

    @ExceptionHandler(BoboException.class)
    @ResponseBody
    public Result handleMyException(BoboException e) {
        return Result.error().code(e.getCode()).message(e.getMsg());
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result handleAuthorizationException(AccessDeniedHandler e) {
        return Result.error().code(ResultCode.FORBIDDEN).message("没有权限，请联系管理员");
    }






}

