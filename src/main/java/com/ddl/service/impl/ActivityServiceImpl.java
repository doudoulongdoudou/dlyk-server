package com.ddl.service.impl;

import com.ddl.config.constant.Constants;
import com.ddl.entity.Activity;
import com.ddl.mapper.ActivityMapper;
import com.ddl.query.ActivityQuery;
import com.ddl.query.BaseQuery;
import com.ddl.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ActivityServiceImpl
 * Package: com.ddl.service.impl
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/11 20:35
 */

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public PageInfo<Activity> getActivityByPage(Integer current, ActivityQuery activityQuery) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<Activity> list = activityMapper.selectActivityByPage(activityQuery);
        PageInfo<Activity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
