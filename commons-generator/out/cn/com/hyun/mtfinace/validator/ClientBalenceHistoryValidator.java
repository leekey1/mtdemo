/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientBalenceHistoryValidator.java
 * Description:       校验ClientBalenceHistoryValidator类定义
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

import cn.com.hyun.mtfinace.dto.ClientBalenceHistoryDto;
import cn.com.hyun.framework.utils.AssertUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static cn.com.hyun.configuration.ApplicationMessage.*;
import java.util.Date;

 /**
  * SVU
  */
public class ClientBalenceHistoryValidator implements Validator {

    public static void validate(ClientBalenceHistoryDto clientBalenceHistoryDto) {
        new ClientBalenceHistoryValidator().validate(clientBalenceHistoryDto, null);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientBalenceHistoryDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    AssertUtils.notNull(target);
    ClientBalenceHistoryDto clientBalenceHistoryDto = (ClientBalenceHistoryDto) target;
 /*
    AssertUtils.notNull(clientBalenceHistoryDto.getId(),"ID不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getMtTickett(),"订单号不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getPrice(),"价格不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getType(),"类型不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getBeforeBalence(),"账户金额前不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getAfterAfter(),"账户金额后不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getEmail(),"用户邮箱不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getLogin(),"交易账户不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getMetaProfileId(),"profileID不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getCreatedAt(),"创建时间不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getCreatedBy(),"创建者不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getUpdatedAt(),"最后更新时间不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getUpdatedBy(),"最后更新者不能为空!");

    AssertUtils.notNull(clientBalenceHistoryDto.getVersion(),"版本不能为空!");

*/

    }




}
