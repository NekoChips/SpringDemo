package com.demo.nekoBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author NekoChips
 * @description SpringBoot 启动类
 * @date 2020/3/24
 */
@SpringBootApplication
@EnableConfigurationProperties()
public class NekoBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(NekoBootApplication.class, args);
    }
}
