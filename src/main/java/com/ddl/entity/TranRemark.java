package com.ddl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_tran_remark
 */
@Data
public class TranRemark implements Serializable {
    private Integer id;

    private Integer tranId;

    private Integer noteWay;

    private String noteContent;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}