package com.bobo.myspringsecurity.security;

import com.alibaba.fastjson.JSON;
import com.bobo.myspringsecurity.common.utils.JwtUtils;
import com.bobo.myspringsecurity.common.utils.Result;
import com.bobo.myspringsecurity.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description 注销处理
 * @Date 2021/10/12 15:47
 * @Created by bobo
 */
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        }
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        Result result = Result.ok().message("退出登录成功").code(ResultCode.SUCCESS);
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
