package com.demo.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * ClassName: SecurityApplication <br/>
 * Description: SecurityApplication 相关<br/>
 * date: 2019/12/5 12:34<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class SecurityApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SecurityApplication.class);
    }
}
