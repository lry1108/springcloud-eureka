package com.lry.config;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyRestTemplateConfig {

    @Bean
//    @LoadBalanced //负载均衡注解
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //修改rabbion的负载均衡机制
//    @Bean
//    public RandomRule randomRule(){
//        return new RandomRule();
//    }
}
