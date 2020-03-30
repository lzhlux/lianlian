package com.lianlian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.lianlian.tank.dao")
public class LianTankApplication {

    public static void main(String[] args) {
        SpringApplication.run(LianTankApplication.class, args);
    }

}
