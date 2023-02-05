/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientChallengeDto.java
 * Description:       实体ClientChallengeDto类定义
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
public class ClientChallengeDto implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "开始日期")
    private Date startDt;

    @ApiModelProperty(value = "结束日期")
    private Date endDt;

    @ApiModelProperty(value = "挑战级别")
    private String challengeLevel;

    @ApiModelProperty(value = "结果")
    private String result;

    @ApiModelProperty(value = "交易天数")
    private String tradingDays;

    @ApiModelProperty(value = "每日最大损失")
    private Integer dailyMaxLoss;

    @ApiModelProperty(value = "最大损失")
    private Integer maxLoss;

    @ApiModelProperty(value = "当前盈利")
    private Integer profitTarget;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "最后更新者")
    private String updatedBy;


    public ClientChallengeDto() {

    }
}
