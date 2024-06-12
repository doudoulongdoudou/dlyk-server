package com.ddl.util;

import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ClassName: CacheUtils
 * Package: com.ddl.util
 * Description:
 * 缓存工具类
 * @Author 豆豆龙
 * @Create 2024/6/12 14:34
 */
public class CacheUtils {

    /**
     * 带有缓存的查询工具方法
     * @param cacheSelector
     * @param dbSelector
     * @param cacheSave
     * @return
     * @param <T>
     */
    public static <T> T getCacheData(Supplier<T> cacheSelector, Supplier<T> dbSelector, Consumer<T> cacheSave){
        //从缓存中查
        T data =cacheSelector.get();
        if (ObjectUtils.isEmpty(data)){
            //redis里为null,就从数据库查
            T db = dbSelector.get();
            if (!ObjectUtils.isEmpty(db)){
                //数据库里查到了，就存入redis
                cacheSave.accept(db);
            }
            return db;
        }else {
            return data;
        }
    }

}
