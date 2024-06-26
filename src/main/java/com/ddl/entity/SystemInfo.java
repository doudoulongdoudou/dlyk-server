package com.ddl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_system_info
 */
@Data
public class SystemInfo implements Serializable {
    private Integer id;

    private String systemCode;

    private String name;

    private String site;

    private String logo;

    private String title;

    private String description;

    private String keywords;

    private String shortcuticon;

    private String tel;

    private String weixin;

    private String email;

    private String address;

    private String version;

    private String closemsg;

    private String isopen;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}