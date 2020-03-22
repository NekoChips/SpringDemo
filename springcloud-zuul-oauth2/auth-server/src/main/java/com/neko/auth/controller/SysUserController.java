package com.neko.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description 用户鉴权信息接口
 * @date 2020/3/22
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @GetMapping("authInfo")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
