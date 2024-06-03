package com.ddl.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_user_role
 */
@Data
public class UserRole implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}