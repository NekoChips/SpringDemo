package com.demo.nekoBoot;

import com.demo.nekoBoot.config.MyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author NekoChips
 * @description SpringBoot 启动类
 * @date 2020/3/24
 */
@SpringBootApplication
public class NekoBootApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NekoBootApplication.class, args);
        // 测试 自定义属性配置
//        MyBlog myBlog = (MyBlog) context.getBean("myBlog");
//        System.out.println(String.format("blog name: %s%nblog address: %s", myBlog.getName(), myBlog.getUrl()));
        MyProperties myProperties = (MyProperties) context.getBean("myProperties");
        System.out.println(myProperties);
    }
}
