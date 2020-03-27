package com.demo.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description 测试 Controller
 * @date 2020/3/27
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    public HttpEntity<?> testLog() {
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("trace 级别日志输出");
        logger.debug("debug 级别日志输出");
        logger.info("info 级别日志输出");
        logger.warn("warn 级别日志输出");
        logger.error("error 级别日志输出");

        return ResponseEntity.ok("hello world");
    }
}
