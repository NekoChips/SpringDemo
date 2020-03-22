package com.neko.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description 测试controller
 * @date 2020/3/20
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("info")
    @PreAuthorize("hasAuthority('user:hello')")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String test() {
        return "Hello user";
    }

    @GetMapping("auth")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
