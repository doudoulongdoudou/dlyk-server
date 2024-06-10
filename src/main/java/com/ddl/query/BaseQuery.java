package com.ddl.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: BaseQuery
 * Package: com.ddl.query
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/9 15:02
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuery {

    //jwt
    private String token;

    //数据权限的SQL过滤条件
    private String filterSQL;

}
