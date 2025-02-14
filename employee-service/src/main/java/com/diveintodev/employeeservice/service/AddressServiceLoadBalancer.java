/*package com.diveintodev.employeeservice.service;


import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(value = "address-service" , configuration = MyCustomLBConfig.class)
//@LoadBalancerClient(value = "address-service")
public class AddressServiceLoadBalancer {

    @LoadBalanced
    @Bean
    public Feign.Builder feiBuilder(){
        return Feign.builder();
    }
}*/
