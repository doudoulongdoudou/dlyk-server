package com.ddl.service.impl;

import com.ddl.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisServiceImpl
 * Package: com.ddl.service.impl
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/5 11:29
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean removeValue(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Boolean expire(String key, Long timeOut, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeOut, timeUnit);
    }
}
