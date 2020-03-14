package com.demo.oauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yangjie.Chen
 * @description 测试 controller
 * @date 2020/3/13
 */
@RestController
public class TestController {

    @GetMapping("index")
    public Object index(Authentication authentication) {
        return authentication;
    }
}
