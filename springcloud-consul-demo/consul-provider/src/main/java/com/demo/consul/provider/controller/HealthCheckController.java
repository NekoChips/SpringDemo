package com.demo.consul.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description 健康检查 controller
 * @date 2020/3/20
 */
@RestController
public class HealthCheckController {

    @GetMapping("healthCheck")
    public void healthCheck() {

    }
}
