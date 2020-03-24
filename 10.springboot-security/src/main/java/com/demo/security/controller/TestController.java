package com.demo.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yangjie.Chen
 * @description 测试 controller
 * @date 2020/3/12
 */
@RestController
public class TestController {

    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("index")
    public Object index(Authentication authentication) {
        // return SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("logout/success")
    public String logout() {
        return "logout success";
    }

    @GetMapping("authInfo")
    @PreAuthorize("hasAnyAuthority('auth:info','ROLE_SUPER')")
    public Authentication authInfo(Authentication authentication) {
        return authentication;
    }

    @GetMapping("testAuth")
    @PreAuthorize("hasAuthority('user:addOne')")
    public String testAuth() {
        return "has authority to visit";
    }

    @GetMapping("testRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testRole() {
        return "has role to visit";
    }

}
