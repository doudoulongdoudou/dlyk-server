package com.ddl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ClassName: RedisConfig
 * Package: com.ddl.config
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/6 20:05
 */

@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 解决redis乱码问题
     * @return
     */
    @Bean
    public RedisTemplate redisTemplateInit(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
