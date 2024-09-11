package com.peppa.test.mapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.peppa.test.mapper")
public class TestStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(TestStarterApp.class,args);
    }
}