package com.peppa.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: peppa
 * @Description: 缓存启动类
 * @Date: Created in 18:34 2024/8/29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CacheStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(CacheStarterApp.class, args);
    }
}
