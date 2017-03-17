package com.gongfu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 2017年1月11日
 *
 * @向治家
 **/
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    /**
     * @return
     */
    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.yhqz.controller")) // 拦截的包路径
                .paths(regex("/api/.*"))// 拦截的接口路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("功夫旅行")// 标题
                .description("功夫旅行平台  《请求接口》")// 描述
                .termsOfServiceUrl("http://www.pkfare.com")//
                //.contact(new Contact("xiangzhijia", "http://www.pkfare.com", "494856845@qq.com"))// 联系
                //.license("Apache License Version 2.0")// 开源协议
                //.licenseUrl("http://www.pkfare.com")// 地址
                .version("1.0")// 版本
                .build();
    }
}