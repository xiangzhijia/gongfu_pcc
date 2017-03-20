package com.gongfu.config;

import com.gongfu.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 2017年1月11日
 *
 * @向治家
 **/
@Slf4j
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.info("MvcConfig→{addViewControllers}");
        // 默认转跳至接口文档页面
        registry.addViewController("/login").setViewName("redirect:swagger-ui.html");

        // 配置登陆页面地址
        registry.addViewController(BaseController.API_ADMIN + "/index/login").setViewName("login");
    }
}
