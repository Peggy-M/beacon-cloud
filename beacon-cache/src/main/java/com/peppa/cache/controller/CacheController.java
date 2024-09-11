package com.peppa.cache.controller;


import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class CacheController {
    @Autowired
    private RedisClient redisClient;

    @PostMapping(value = "/cache/hmset/{key}")
    public void hmset(@PathVariable(value = "key") String key, @RequestBody Map<String, Object> map) {
        log.info("【缓存模块】 hmset方法，存储key = {}，存储value = {}", key, map);
        redisClient.hSet(key, map);
    }

    @PostMapping(value = "/cache/set/{key}")
    public void set(@PathVariable(value = "key") String key, @RequestParam(value = "value") String value) {
        log.info("【缓存模块】 set方法，存储key = {}，存储value = {}", key, value);
        redisClient.set(key, value);
    }

    @PostMapping(value = "/cache/sadd/{key}")
    public void sadd(@PathVariable(value = "key")String key, @RequestBody Map<String,Object>... value){
        log.info("【缓存模块】 sadd方法，存储key = {}，存储value = {}", key, value);
        redisClient.sAdd(key,value);
    }
}