package com.peppa.cache.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: peppa
 * @Description: 设置RedisTemplate的序列化方式
 * @Date: Created in 18:45 2024/8/29
 */
//@Configuration
public class RedisConfig {

    @Bean
    public <T>RedisTemplate<String,T> redisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer<Object> redisSerializer) {

        // 1. 构建 RedisTemplate 对象
        RedisTemplate<String,T> redisTemplate = new RedisTemplate<>();

        //2. 设置RedisConnectionFactory到RedisTemplate
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //3. 设置Redis的key的序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        //3. 设置Redis的value的序列化方式
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer(){
        //1. 构建Jackson的ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //2. 设置JDK8日期格式的支持
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Module timeModule = new JavaTimeModule()
                .addDeserializer(LocalDate.class,new LocalDateDeserializer(dateFormatter))
                .addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(dateTimeFormatter))
                .addSerializer(LocalDate.class,new LocalDateSerializer(dateFormatter))
                .addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(dateTimeFormatter));
        objectMapper.registerModule(timeModule);

        //3. 构建Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        //4. 设置好对JDK8日期的支持
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //5. 返回设置好的Jackson2JsonRedisSerializer
        return jackson2JsonRedisSerializer;
    }
}
