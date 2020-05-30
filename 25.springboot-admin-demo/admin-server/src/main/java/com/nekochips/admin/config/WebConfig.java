package com.nekochips.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/**
 * @author NekoChips
 * @description Spring Security 配置类
 * @date 2020/5/29
 */
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServer;

    public WebConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        String contextPath = this.adminServer.getContextPath();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(contextPath + "/");

        http
                .authorizeRequests()
                    .antMatchers(contextPath + "/assets/**", contextPath + "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage(contextPath + "/login")
                    .successHandler(successHandler)
                    .and()
                .logout()
                    .logoutUrl(contextPath + "/logout")
                    .and()
                .httpBasic()
                    .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .ignoringRequestMatchers(
                            new AntPathRequestMatcher(contextPath + "/instances", HttpMethod.POST.toString()),
                            new AntPathRequestMatcher(contextPath + "/instances/*", HttpMethod.DELETE.toString()),
                            new AntPathRequestMatcher(contextPath + "/actuator/**", HttpMethod.GET.toString()))
                    .and()
                .rememberMe()
                    .key(UUID.randomUUID().toString())
                    .tokenValiditySeconds(1209600);
                
    }
}
