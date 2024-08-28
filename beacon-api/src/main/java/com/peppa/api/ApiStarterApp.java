package com.peppa.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@ComponentScan(basePackages = { "com.peppa.api", "com.peppa.common.core"})
public class ApiStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiStarterApp.class,args);
    }
}
