package com.lry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//开启eureka注解 目前版本如果配置了Eureka注册中心,默认会开启该注解
//@EnableEurekaClient
@SpringBootApplication
public class ServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }
}
