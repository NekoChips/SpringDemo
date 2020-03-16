package com.demo.oauth2.config;

import com.demo.oauth2.handler.MyAuthenticationFailureHandler;
import com.demo.oauth2.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Yangjie.Chen
 * @description 资源服务器配置
 * @date 2020/3/13
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    // 注入认证成功处理器
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    // 注入认证失败处理器
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    /**
     * 设置需要 token 验证的 url
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 开启表单登录
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()

                // 配置授权
                .authorizeRequests()
                // 所有请求均需要认证后才能访问
                .anyRequest().authenticated()
                .and()

                // 关闭跨域请求伪造
                .csrf().disable();
    }
}
