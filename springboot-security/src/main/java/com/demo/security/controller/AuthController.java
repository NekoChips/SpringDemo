package com.demo.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yangjie.Chen
 * @description 认证 controller
 * @date 2020/3/12
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 根据不同的资源访问做不同的处理，
     * 访问以 .html 结尾的资源，跳转至登录页面，
     * 访问其他形式的资源，返回 please login first ，并且状态码为 HttpStatus.UNAUTHORIZED
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("login")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }
        }
        return "please login first";
    }

    @GetMapping("session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionExpired() {
        return "session expired";
    }
}
