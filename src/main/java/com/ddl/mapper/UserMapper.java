package com.ddl.mapper;

import com.ddl.entity.User;

import java.util.List;

/**
* @author 10795
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2024-06-03 15:10:31
* @Entity com.ddl.entity.User
*/
public interface UserMapper {

    /**
     * 查询用户名
     * @param username
     * @return
     */
    User selectByLoginAct(String username);

    /**
     * 分页查询 所有用户信息
     * @return
     */
    List<User> selectUserByPage();
}




