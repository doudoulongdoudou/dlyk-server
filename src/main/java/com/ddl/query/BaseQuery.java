package com.ddl.query;

import lombok.AllArgsConstructor;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuery {

    //jwt
    private String token;
}
