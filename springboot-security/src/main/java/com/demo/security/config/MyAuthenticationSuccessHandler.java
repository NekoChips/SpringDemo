package com.demo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yangjie.Chen
 * @description 自定义登录成功处理器
 * @date 2020/3/12
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper mapper;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 将 认证信息 打印出去
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(mapper.writeValueAsString(authentication));

        // 配置登录成功后的页面跳转
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
        redirectStrategy.sendRedirect(request, response, "/index");
    }
}
