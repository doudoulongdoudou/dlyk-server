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

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User selectDetailById(Integer id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 编辑用户
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delUserById(Integer id);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    int batchDelUserByIds(List<String> idList);
}




