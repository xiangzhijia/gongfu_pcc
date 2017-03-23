package com.gongfu.config;

import com.google.common.base.Optional;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 2017年1月11日
 *
 * @向治家
 **/
@Configuration
@EnableSwagger2
@Slf4j
public class Swagger2Configuration {

    @Bean
    public Docket accessToken() {
        Parameter clientParameter = new Parameter("x-gongfu-client", "客户端信息", "{\"role\":\"ADVISER\",\"version\":\"v1.1.1\",\"deviceId\":\"1234fads\"}", true, false, new ModelRef("String"), Optional.absent(), null, "header", null, false, null);
        Parameter tokenParameter = new Parameter("x-gongfu-token", "访问令牌", "dobkvu7ukcmd3mjv9e53d86mio", true, false, new ModelRef("String"), Optional.absent(), null, "header", null, false, null);
        List<Parameter> list = Arrays.asList(clientParameter, tokenParameter);

        return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
                .globalOperationParameters(list)
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) // 拦截的包路径
                .paths(regex("/api/.*"))// 拦截的接口路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("功夫旅行")// 标题
                .description("功夫旅行平台  《请求接口》")// 描述
                .termsOfServiceUrl("http://www.pkfare.com")
                .version("1.0")// 版本
                .build();
    }
}
