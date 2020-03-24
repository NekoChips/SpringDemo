package com.demo.nekoBoot.config;

import com.demo.nekoBoot.NekoBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description 自定义博客属性测试
 * @date 2020/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NekoBootApplication.class)
public class TestMyBlog {

    @Autowired
    private MyBlog myBlog;

    @Test
    public void testBlogProps() {
        System.out.format("%s : %s", myBlog.getName(), myBlog.getUrl());
    }
}
