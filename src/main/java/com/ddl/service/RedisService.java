package com.ddl.service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisService
 * Package: com.ddl.service
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/5 11:28
 */
public interface RedisService {

    void setValue(String key, Object value);

    Object getValue(String key);

    Boolean removeValue(String key);

    /**
     * 设置过期时间
     * @param key
     * @param timeOut   过期时间时长
     * @param timeUnit  时间单位
     * @return
     */
    Boolean expire(String key, Long timeOut, TimeUnit timeUnit);
}
