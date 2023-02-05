/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientBalenceHistoryDto.java
 * Description:       实体ClientBalenceHistoryDto类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2023-02-05             hyun auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.Date;

@Data
public class ClientBalenceHistoryDto implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String mtTickett;

    @ApiModelProperty(value = "价格")
    private Integer price;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "账户金额前")
    private Integer beforeBalence;

    @ApiModelProperty(value = "账户金额后")
    private Integer afterAfter;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "交易账户")
    private Integer login;

    @ApiModelProperty(value = "profileID")
    private String metaProfileId;

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


    public ClientBalenceHistoryDto() {

    }
}
