package com.demo.nekoBoot.config;

import com.demo.nekoBoot.NekoBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description 测试自定义配置属性读取
 * @date 2020/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NekoBootApplication.class)
public class TestMyProperties {

    @Autowired
    private MyProperties myProperties;

    @Test
    public void testProps() {
        System.out.println("appName: " + myProperties.getAppName());
        System.out.println("address: " + myProperties.getAddress());
        System.out.println("port: " + myProperties.getPort());
        System.out.println("author: " + myProperties.getAuthor());
        System.out.println("version: " + myProperties.getVersion());
        System.out.println("date: " + myProperties.getDate());
    }
}
