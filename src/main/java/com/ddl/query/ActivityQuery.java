package com.ddl.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName: ActivityQuery
 * Package: com.ddl.query
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/12 21:39
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityQuery extends BaseQuery {

    //负责人Id
    private Integer ownerId;

    //活动名称
    private String name;

    //活动开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //活动结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //活动预算
    private BigDecimal cost;
}
