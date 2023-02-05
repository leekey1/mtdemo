/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        Client.java
 * Description:       实体Client类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2023-02-05             hyun auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Client implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "姓")
    private String fristName;

    @ApiModelProperty(value = "名")
    private String secondName;

    @ApiModelProperty(value = "性别")
    private String title;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "地址")
    private String adress;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "邮编号码")
    private String zip;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "时区")
    private String timeZone;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "最后更新者")
    private String updatedBy;


    public Client() {

    }
}
