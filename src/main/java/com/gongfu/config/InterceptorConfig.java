package com.gongfu.config;

import com.gongfu.config.interceptor.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

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
     * Interceptor initialization
     *******************************/
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    /********************************
     * Interceptor register
     ********************************/
    @Bean
    public MappedInterceptor interceptor() {
        /**
         * 参数1:拦截匹配路径
         * 参数2：排除那些路径
         * 参数3: 拦截器
         */
        return new MappedInterceptor(new String[]{"/api/**"}, null, authInterceptor());
    }

    /********************************
     * 配置参数解析器（用于接收参数）
     *******************************/
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        log.info("InterceptorConfig→{addArgumentResolvers}");
    }
}
