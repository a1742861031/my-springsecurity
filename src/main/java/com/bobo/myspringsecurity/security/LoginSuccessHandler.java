package com.bobo.myspringsecurity.security;

import com.alibaba.fastjson.JSON;
import com.bobo.myspringsecurity.common.utils.JwtUtils;
import com.bobo.myspringsecurity.common.utils.Result;
import com.bobo.myspringsecurity.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description 登录成功处理器
 * @Date 2021/10/11 11:28
 * @Created by bobo
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        //生成jwt并返回
        //获取名称
        String jwt = jwtUtils.generateToken(authentication.getName());
        Result result = Result.ok().message("登录成功").code(ResultCode.SUCCESS).data("token", jwt);
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
