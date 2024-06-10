package com.ddl.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserQuery
 * Package: com.ddl.query
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/9 13:50
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery extends BaseQuery {

    private Integer id;

    private String loginAct;

    private String loginPwd;

    private String name;

    private String phone;

    private String email;

    private Integer accountNoExpired;

    private Integer credentialsNoExpired;

    private Integer accountNoLocked;

    private Integer accountEnabled;

}
