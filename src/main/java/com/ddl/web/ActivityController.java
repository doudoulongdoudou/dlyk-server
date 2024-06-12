package com.ddl.web;

import com.ddl.entity.Activity;
import com.ddl.query.ActivityQuery;
import com.ddl.result.R;
import com.ddl.service.ActivityService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ActivityController
 * Package: com.ddl.web
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/11 20:31
 */

@Slf4j
@RestController
public class ActivityController {

    @Resource
    private ActivityService activityService;

    /**
     * 【分页查询】 查询市场活动
     * @param current
     * @return
     */
    @GetMapping("/api/activity/list")
    public R getActivityList(@RequestParam(value = "current" ,required = false) Integer current , ActivityQuery activityQuery) {
        if (current == null) {
            current = 1;
        }

        PageInfo<Activity> activityPageInfo = activityService.getActivityByPage(current, activityQuery);
        return R.OK(activityPageInfo);
    }

}
