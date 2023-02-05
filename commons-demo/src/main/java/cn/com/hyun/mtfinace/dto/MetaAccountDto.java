/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaAccountDto.java
 * Description:       实体MetaAccountDto类定义
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
public class MetaAccountDto implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "Metaapi 用户ID")
    private String accountId;

    @ApiModelProperty(value = "Metaapi profile ID")
    private String profileId;

    @ApiModelProperty(value = "投资密码只读")
    private String investorPassword;

    @ApiModelProperty(value = "交易账号")
    private String login;

    @ApiModelProperty(value = "交易密码")
    private String password;

    @ApiModelProperty(value = "服务器组")
    private String group;

    @ApiModelProperty(value = "杠杆比")
    private Integer leverage;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "Lead source")
    private String leadSource;

    @ApiModelProperty(value = "是否启用")
    private Integer enabled;

    @ApiModelProperty(value = "货币")
    private String currency;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "最后更新者")
    private String updatedBy;


    public MetaAccountDto() {

    }
}
