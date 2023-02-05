/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaProfileDto.java
 * Description:       实体MetaProfileDto类定义
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
public class MetaProfileDto implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "服务详细名")
    private String broker;

    @ApiModelProperty(value = "服务名")
    private String server;

    @ApiModelProperty(value = "Profile ID")
    private String apiProfileId;

    @ApiModelProperty(value = "profile名")
    private String apiProfileName;

    @ApiModelProperty(value = "时区")
    private String brokerTimezone;

    @ApiModelProperty(value = "服务时区")
    private String brokerDstSwitchTimezone;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "最后更新者")
    private String updatedBy;


    public MetaProfileDto() {

    }
}
