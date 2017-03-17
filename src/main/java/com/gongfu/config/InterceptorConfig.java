package com.gongfu.config;

import com.gongfu.config.interceptor.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 2017年1月8日
 *
 * @向治家
 * @Configuration 等价于与XML中配置beans
 * @Bean 等价于与XML中配置bean
 **/
@Slf4j
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /********************************
     * 添加注册拦截器
     *******************************/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("InterceptorConfig→{addInterceptors}");
        //多个拦截器组成一个拦截器链
        //第一个拦截器
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
        //第个二拦截器
        //reg stry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        log.info("InterceptorConfig→{addArgumentResolvers}");
        //argumentResolvers.add(authInterceptor());
    }
}
