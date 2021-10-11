package com.bobo.myspringsecurity.security;

import com.bobo.myspringsecurity.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 验证码校验过滤器
 * @Date 2021/10/11 12:17
 * @Created by bobo
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //判定url是否为login请求
        String url = httpServletRequest.getRequestURI();
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
            //校验验证码
            boolean validate = validate(httpServletRequest);
            if (!validate) {
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, new AuthenticationException("验证码错误") {
                });
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //校验验证码
    private boolean validate(HttpServletRequest httpServletRequest) {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(key)) {
            return false;
        }
        if (!code.equals(redisUtils.hget("captcha", key))) {
            redisUtils.hdel("captcha", key);//删除验证码
            return false;
        }
        redisUtils.hdel("captcha", key);//删除验证码
        return true;
    }
}
