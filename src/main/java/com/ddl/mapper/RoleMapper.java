package com.ddl.mapper;

import com.ddl.entity.Role;

import java.util.List;

/**
* @author 10795
* @description 针对表【t_role(角色表)】的数据库操作Mapper
* @createDate 2024-06-03 15:10:31
* @Entity com.ddl.entity.Role
*/
public interface RoleMapper {

    /**
     * 根据用户id查询用户的角色
     * @param id
     * @return
     */
    List<Role> selectUserRoleByUserId(Integer userId);
}




