package com.gongfu;

import com.gongfu.base.PccBaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement    //开启事务支持后
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.gongfu.mapper.*", markerInterface = PccBaseMapper.class)
public class GongFuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GongFuApplication.class, args);
    }
}
