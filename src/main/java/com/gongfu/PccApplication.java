package com.gongfu;

import com.gongfu.base.PccBaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement    //开启事务支持后
@EnableAsync
@MapperScan(basePackages = "com.gongfu.mapper.*", markerInterface = PccBaseMapper.class)
public class PccApplication {

    public static void main(String[] args) {
        SpringApplication.run(PccApplication.class, args);
    }
}
