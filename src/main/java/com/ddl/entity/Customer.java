package com.ddl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_customer
 */
@Data
public class Customer implements Serializable {
    private Integer id;

    private Integer clueId;

    private Integer product;

    private String description;

    private Date nextContactTime;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}