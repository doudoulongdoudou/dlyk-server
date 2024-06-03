package com.ddl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_clue
 */
@Data
public class Clue implements Serializable {
    private Integer id;

    private Integer ownerId;

    private Integer activityId;

    private String fullName;

    private Integer appellation;

    private String phone;

    private String weixin;

    private String qq;

    private String email;

    private Integer age;

    private String job;

    private BigDecimal yearIncome;

    private String address;

    private Integer needLoan;

    private Integer intentionState;

    private Integer intentionProduct;

    private Integer state;

    private Integer source;

    private String description;

    private Date nextContactTime;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}