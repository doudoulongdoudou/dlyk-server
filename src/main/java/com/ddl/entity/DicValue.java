package com.ddl.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_dic_value
 */
@Data
public class DicValue implements Serializable {
    private Integer id;

    private String typeCode;

    private String typeValue;

    private Integer order;

    private String remark;

    private static final long serialVersionUID = 1L;
}