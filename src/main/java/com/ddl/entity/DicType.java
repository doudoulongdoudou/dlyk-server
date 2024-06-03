package com.ddl.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_dic_type
 */
@Data
public class DicType implements Serializable {
    private Integer id;

    private String typeCode;

    private String typeName;

    private String remark;

    private static final long serialVersionUID = 1L;
}