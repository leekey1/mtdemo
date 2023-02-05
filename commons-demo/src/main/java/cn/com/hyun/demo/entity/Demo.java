/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        DemoDto.java
 * Description:       实体DemoDto类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2017-11-17             hyun auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Demo implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "进件ID")
    private String loanId;

    @ApiModelProperty(value = "操作人")
    private String demoOpprator;

    @ApiModelProperty(value = "操作类型")
    private String demoOppratorType;

    @ApiModelProperty(value = "操作时间")
    private Date demoOppratorTime;

    @ApiModelProperty(value = "金额")
    private BigDecimal demoMoney;

    @ApiModelProperty(value = "比列")
    private BigDecimal demoRate;

    @ApiModelProperty(value = "借款月")
    private Integer demoMonth;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "最后更新者")
    private String updatedBy;

    @ApiModelProperty(value = "版本")
    private Integer version;



    public Demo() {

    }
}
