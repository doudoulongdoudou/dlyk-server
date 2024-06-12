package com.ddl.manager;

import com.ddl.config.constant.Constants;
import com.ddl.util.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: RedisManager
 * Package: com.ddl.manager
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/12 16:35
 */

@Component
public class RedisManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 从redis获取数据
     *
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 存数据进redis
     *
     * @param key
     * @param data
     * @return
     */
    public <T> Object setValue(String key, Collection<T> data) {
        Object[] t = new Object[data.size()];
        data.toArray(t);
        return redisTemplate.opsForList().leftPushAll(key, t);
    }
}
