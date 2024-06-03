package com.ddl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_user
 */
@Data
public class User implements Serializable {
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

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;
}