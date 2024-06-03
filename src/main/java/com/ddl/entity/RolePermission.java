package com.ddl.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_role_permission
 */
@Data
public class RolePermission implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;
}