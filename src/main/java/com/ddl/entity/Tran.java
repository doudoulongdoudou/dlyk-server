package com.ddl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_tran
 */
@Data
public class Tran implements Serializable {
    private Integer id;

    private String tranNo;

    private Integer customerId;

    private BigDecimal money;

    private Date expectedDate;

    private Integer stage;

    private String description;

    private Date nextContactTime;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}