package com.ddl.service;

import com.ddl.entity.User;
import com.ddl.query.UserQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * ClassName: UserService
 * Package: com.ddl.service
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/3 15:52
 */

public interface UserService extends UserDetailsService {

    /**
     * 【分页查询】 查询所有用户信息
     * @param current
     * @return
     */
    PageInfo<User> getUserByPage(Integer current);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 新增用户
     * @param userQuery
     * @return
     */
    int saveUser(UserQuery userQuery);

}
