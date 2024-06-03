package com.ddl.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_role
 */
@Data
public class Role implements Serializable {
    private Integer id;

    private String role;

    private String roleName;

    private static final long serialVersionUID = 1L;
}