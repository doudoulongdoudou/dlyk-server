package com.ddl.mapper;

import com.ddl.commons.DataScope;
import com.ddl.entity.Activity;
import com.ddl.query.ActivityQuery;
import com.ddl.query.BaseQuery;

import java.util.List;

/**
* @author 10795
* @description 针对表【t_activity(市场活动表)】的数据库操作Mapper
* @createDate 2024-06-03 15:10:31
* @Entity com.ddl.entity.Activity
*/
public interface ActivityMapper {

    /**
     * 【分页查询】 查询市场活动
     * @param build
     * @return
     */
    @DataScope(tableAlias = "ta", tableField = "owner_id")
    List<Activity> selectActivityByPage(ActivityQuery activityQuery);
}




