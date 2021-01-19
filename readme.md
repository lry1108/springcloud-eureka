#Spring Cloud Netflix 第一代
+ Netflix Eureka 基于rest服务的服务治理组件，包括服务注册中心、服务注册与服务发现机制的实现
+ Netflix Hystrix 容错管理工具，实现断路器模式
+ Netflix Ribbon 客户端负载均衡
+ Netflix Feign 声明式负载均衡,封装了Ribbon和Hystrix
+ Netflix Zuul 微服务网关，提供动态路由、访问过滤等服务
+ Netflix Archaius 配置管理API

#Spring Cloud Alibaba 第二代
+ Nacos 注册中心、配置中心
+ Sentinel 流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性
+ RocketMQ 消息中间件
+ Dubbo JAVA RPC框架
+ Seata 分布式事务

#Spring Cloud官方
+ Spring Cloud OpenFeign可替代Feign
+ Spring Cloud Gateway 可替代Zuul
+ Spring Cloud Config 分布式配置中心
+ Spring Cloud Bus 事件、消息总线
+ Spring Cloud Stream 消息驱动微服务
+ Spring Cloud Sleuth 分布式服务跟踪

#常见的注册中心有：CAP 高度一致性、高可用性、分区容错性
Netflix Eureka  AP
Alibaba Nacos  AP+CP
HashiCorp Consul CP
Apache ZooKeeper CP
CoreOS Etcd
CNCF coreDNS

#负载均衡分类：
+ 集中式负载均衡(服务器负载均衡)，独立的负载均衡设施，可以是硬件，如F5；也可以是软件，如nginx
+ 进程内负载均衡 如rabbion，consumer从服务注册中心选择合适的provider

#修改rabbion的负载均衡策略：
+ 全局的:在启动类或者配置类中，注入负载均衡策略对象；所有服务请求均使用该策略
```java
    @Bean
    public RandomRule randomRule(){
        return new RandomRule();
    }
```
+ 局部的：在配置文件中指定服务使用LB策略
```yaml
service-provider:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```
+ Rabbion还可以实现 点对点直连，即不需要eureka，consumer直接请求到provider
测试阶段可以点对点直连，不经过注册中心