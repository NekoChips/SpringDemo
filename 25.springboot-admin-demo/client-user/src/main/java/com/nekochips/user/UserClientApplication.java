package com.nekochips.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author NekoChips
 * @description 被 Spring Boot Admin 监控的用户模块启动类
 * @date 2020/5/29
 */
@SpringBootApplication
public class UserClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication.class, args);
    }
}
