package com.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author NekoChips
 * @description Swagger 配置类
 * @date 2020/3/26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api文档介绍信息
     *
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 测试 RESTful Api 文档")
                .contact(new Contact("NekoChips", "https://chenyangjie.com.cn", "shupian421182@outlook.com"))
                .version("v1.0")
                .build();
    }
}
