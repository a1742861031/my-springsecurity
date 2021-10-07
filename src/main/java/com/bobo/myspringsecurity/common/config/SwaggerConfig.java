package com.bobo.myspringsecurity.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 使用swagger
 * @author: bobo
 * @create: 2021-10-07 10:57
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("bobo Security API")//组名称
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bobo.myspringsecurity"))//扫描的包
                .paths(PathSelectors.any())
                .build();

    }
    /**
     * 该套 API 说明，包含作者、简介、版本、等信息
     *
     */
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("bobo-springsecurity-API文档")
                .description("本文档描述了bobo-springsecurity接口定义")
                .version("1.0")
                .build();
    }
}
