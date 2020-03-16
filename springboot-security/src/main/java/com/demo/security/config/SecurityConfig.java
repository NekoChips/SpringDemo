package com.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author Yangjie.Chen
 * @description 表单认证security配置类
 * @date 2020/3/12
 */
@Configuration
//@EnableWebSecurity
// 开启注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 用户信息查询接口
    @Autowired
    private UserDetailsService userDetailsService;

    // 自定义认证成功处理器
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    // 自定义认证失败处理器
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    // 数据源
    @Autowired
    private DataSource dataSource;

    // session 限制登录相关配置
    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;

    // 配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // jdbcToken 持久化对象
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单认证方式
        http.formLogin()
                // 配置登录页面
                .loginPage("/login.html")
//                .loginPage("/auth/login")
                // 处理表单登录
                .loginProcessingUrl("/login")
                // 配置认证成功处理器
                .successHandler(successHandler)
                // 配置认证失败处理器
                .failureHandler(failureHandler)

                .and()
                // rememberMe 相关配置
                .rememberMe()
                // token持久化仓库
                .tokenRepository(tokenRepository())
                // 过期时间，1个小时
                .tokenValiditySeconds(3600)
                // 处理自动登录逻辑
                .userDetailsService(userDetailsService)
                .and()

                // 配置相关授权
                .authorizeRequests()
                // 不拦截的请求
                .antMatchers("/login.html","/auth/session/invalid", "/logout/success").permitAll()
                // 所有的请求都需要经过认证
                .anyRequest().authenticated()

                .and()
                // 添加session管理器
                .sessionManagement()
                // session 失效后跳转的地址
                .invalidSessionUrl("/auth/session/invalid")
                .maximumSessions(1)
                .expiredSessionStrategy(sessionExpiredStrategy)
                .and()

                // 退出配置
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
                .deleteCookies("JSESSIONID")

                .and()
                // 关闭跨域请求伪造
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
