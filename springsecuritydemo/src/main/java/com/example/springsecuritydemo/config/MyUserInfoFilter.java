package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class MyUserInfoFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //区分是json传参,还是form表单传参
        String contentType = request.getContentType();
        boolean isJSON = Objects.equals(contentType, MediaType.APPLICATION_JSON_VALUE);
        String code = null;
        if (isJSON) {
            //JSON传参
            String userName = null;
            String password = null;
            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                userName = map.get(super.getUsernameParameter());
                password = map.get(super.getPasswordParameter());
                code = map.get("code");
            } catch (IOException e) {
                log.warn("获取JSON数据失败", e);
            } finally {
                checkVerifyCode(code, request);
            }
            userName = (userName != null) ? userName : "";
            userName = userName.trim();
            password = (password != null) ? password : "";
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            //form表单形式
            checkVerifyCode(request.getParameter("code"), request);
            return super.attemptAuthentication(request, response);
        }
    }

    private void checkVerifyCode(String code, HttpServletRequest request) throws AuthenticationException {
        Object sessionCode = request.getSession().getAttribute("code");
        if (ObjectUtils.isEmpty(code) || ObjectUtils.isEmpty(sessionCode) || !Objects.equals(sessionCode, code)) {
            //验证码不相等
            throw new AuthenticationServiceException("验证码错误");
        }
    }
}
