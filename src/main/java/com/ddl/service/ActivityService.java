package com.ddl.service;

import com.ddl.entity.Activity;
import com.ddl.query.ActivityQuery;
import com.github.pagehelper.PageInfo;

/**
 * ClassName: ActivityService
 * Package: com.ddl.service
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/11 20:34
 */
public interface ActivityService {

    /**
     * 【分页查询】 查询市场活动
     * @param current
     * @return
     */
    PageInfo<Activity> getActivityByPage(Integer current, ActivityQuery activityQuery);
}
