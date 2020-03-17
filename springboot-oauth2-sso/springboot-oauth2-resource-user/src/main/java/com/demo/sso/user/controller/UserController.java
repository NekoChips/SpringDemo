package com.demo.sso.user.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Yangjie.Chen
 * @description 用户controller
 * @date 2020/3/17
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 获取 用户信息
     * @param principal 用户信息
     * @return 用户信息
     */
    @GetMapping("principal")
    @PreAuthorize("hasAuthority('user:principal')")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("authInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<Authentication> authInfo(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }
}
