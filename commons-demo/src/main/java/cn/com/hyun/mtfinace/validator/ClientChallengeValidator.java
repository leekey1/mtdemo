/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientChallengeValidator.java
 * Description:       校验ClientChallengeValidator类定义
 *
 * Dependencies:
 *
 * History:
 *     Date                   Modifier              Log
 *     2023-02-05             hyun auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.validator;

import cn.com.hyun.mtfinace.dto.ClientChallengeDto;
import cn.com.hyun.framework.utils.AssertUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static cn.com.hyun.configuration.ApplicationMessage.*;
import java.util.Date;

 /**
  * SVU
  */
public class ClientChallengeValidator implements Validator {

    public static void validate(ClientChallengeDto clientChallengeDto) {
        new ClientChallengeValidator().validate(clientChallengeDto, null);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientChallengeDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    AssertUtils.notNull(target);
    ClientChallengeDto clientChallengeDto = (ClientChallengeDto) target;
 /*
    AssertUtils.notNull(clientChallengeDto.getId(),"ID不能为空!");

    AssertUtils.notNull(clientChallengeDto.getEmail(),"用户邮箱不能为空!");

    AssertUtils.notNull(clientChallengeDto.getStartDt(),"开始日期不能为空!");

    AssertUtils.notNull(clientChallengeDto.getEndDt(),"结束日期不能为空!");

    AssertUtils.notNull(clientChallengeDto.getChallengeLevel(),"挑战级别不能为空!");

    AssertUtils.notNull(clientChallengeDto.getResult(),"结果不能为空!");

    AssertUtils.notNull(clientChallengeDto.getTradingDays(),"交易天数不能为空!");

    AssertUtils.notNull(clientChallengeDto.getDailyMaxLoss(),"每日最大损失不能为空!");

    AssertUtils.notNull(clientChallengeDto.getMaxLoss(),"最大损失不能为空!");

    AssertUtils.notNull(clientChallengeDto.getProfitTarget(),"当前盈利不能为空!");

    AssertUtils.notNull(clientChallengeDto.getCreatedAt(),"创建时间不能为空!");

    AssertUtils.notNull(clientChallengeDto.getCreatedBy(),"创建者不能为空!");

    AssertUtils.notNull(clientChallengeDto.getUpdatedAt(),"最后更新时间不能为空!");

    AssertUtils.notNull(clientChallengeDto.getUpdatedBy(),"最后更新者不能为空!");

*/

    }




}
