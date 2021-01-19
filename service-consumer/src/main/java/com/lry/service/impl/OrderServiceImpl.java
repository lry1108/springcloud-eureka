package com.lry.service.impl;

import com.lry.pojo.Order;
import com.lry.pojo.Product;
import com.lry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 对于服务的消费提供三种方式：
 * 1 DiscoveryClient 通过元数据获取服务信息
 * 2 LoadBalancerClient  Ribbon的负载均衡器
 * 3 注解@LoadBalanced 通过注解开启Ribbon的负载均衡器
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient; //Ribbon负载均衡器

    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id, "order-001", "中国", 888889D,
                selectProductListByLoadBalancerClient());
    }

    private List<Product> selectProductListByDiscoveryClient() {
        StringBuffer sb = null;
        //获取服务列表
        List<String> serviceIds = discoveryClient.getServices();
        if(CollectionUtils.isEmpty(serviceIds)){
            return null;
        }

        //根据服务名 获取服务
        List<ServiceInstance> instances = discoveryClient.getInstances("SERVICE-PROVIDER");
        if(CollectionUtils.isEmpty(instances)){
            return null;
        }
        ServiceInstance serviceInstance = instances.get(0);
        sb = new StringBuffer();
        sb.append("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/list");

        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }

    private List<Product> selectProductListByLoadBalancerClient() {
        StringBuffer sb = null;

        //根据服务名 获取服务
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
        if(null == serviceInstance){
            return null;
        }

        sb = new StringBuffer();
        sb.append("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/list");
        System.out.println(sb.toString());

        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }

    //注解方式： 需要在RestTemplate配置类加上 注解@LoadBalanced
    private List<Product> selectProductListByLoadBalancerAnnotation() {
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                "http://service-provider/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }


}
