package com.ddl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_tran_history
 */
@Data
public class TranHistory implements Serializable {
    private Integer id;

    private Integer tranId;

    private Integer stage;

    private BigDecimal money;

    private Date expectedDate;

    private Date createTime;

    private Integer createBy;

    private static final long serialVersionUID = 1L;
}