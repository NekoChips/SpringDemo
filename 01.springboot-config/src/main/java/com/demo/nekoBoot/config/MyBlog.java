package com.demo.nekoBoot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author NekoChips
 * @description 自定义博客属性
 * @date 2020/3/24
 */
@Configuration
public class MyBlog {
    @Value("${neko.blog.name:NekoChips}")
    private String name;

    @Value("${neko.blog.url:https://hacpai.com}")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
