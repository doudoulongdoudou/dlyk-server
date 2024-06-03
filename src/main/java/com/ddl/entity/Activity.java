package com.ddl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_activity
 */
@Data
public class Activity implements Serializable {
    private Integer id;

    private Integer ownerId;

    private String name;

    private Date startTime;

    private Date endTime;

    private BigDecimal cost;

    private String description;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}