package com.ddl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_customer_remark
 */
@Data
public class CustomerRemark implements Serializable {
    private Integer id;

    private Integer customerId;

    private Integer noteWay;

    private String noteContent;

    private Integer createBy;

    private Date createTime;

    private Date editTime;

    private Integer editBy;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}