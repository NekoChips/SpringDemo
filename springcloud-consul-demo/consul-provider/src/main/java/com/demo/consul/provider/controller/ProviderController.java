package com.demo.consul.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description 服务提供者测试controller
 * @date 2020/3/20
 */
@RestController
@RequestMapping("provider")
public class ProviderController {

    @GetMapping("echo/{message}")
    public String echo(@PathVariable String message) {
        return "Hello Consul Discovery " + message;
    }
}
