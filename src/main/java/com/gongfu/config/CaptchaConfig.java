package com.gongfu.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb
 * CaptchaConfig.java
 **/
@Slf4j
@Configuration
public class CaptchaConfig {
    @Bean
    public Producer defaultKaptcha() {
        log.info("init→Kaptcha");
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.image.width", 80);
        properties.put("kaptcha.image.height", 36);
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.put("kaptcha.textproducer.font.size", 30);
        properties.put("kaptcha.textproducer.char.length", 4);
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.font.names", "Times New Roman");
        properties.put("kaptcha.background.clear.from", "227,237,247");
        properties.put("kaptcha.background.clear.to", "227,237,247");
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
