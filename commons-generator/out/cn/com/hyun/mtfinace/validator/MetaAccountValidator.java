/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaAccountValidator.java
 * Description:       校验MetaAccountValidator类定义
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

import cn.com.hyun.mtfinace.dto.MetaAccountDto;
import cn.com.hyun.framework.utils.AssertUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static cn.com.hyun.configuration.ApplicationMessage.*;
import java.util.Date;

 /**
  * SVU
  */
public class MetaAccountValidator implements Validator {

    public static void validate(MetaAccountDto metaAccountDto) {
        new MetaAccountValidator().validate(metaAccountDto, null);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MetaAccountDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    AssertUtils.notNull(target);
    MetaAccountDto metaAccountDto = (MetaAccountDto) target;
 /*
    AssertUtils.notNull(metaAccountDto.getId(),"ID不能为空!");

    AssertUtils.notNull(metaAccountDto.getEmail(),"用户邮箱不能为空!");

    AssertUtils.notNull(metaAccountDto.getAccountId(),"Metaapi 用户ID不能为空!");

    AssertUtils.notNull(metaAccountDto.getProfileId(),"Metaapi profile ID不能为空!");

    AssertUtils.notNull(metaAccountDto.getInvestorPassword(),"投资密码只读不能为空!");

    AssertUtils.notNull(metaAccountDto.getLogin(),"交易账号不能为空!");

    AssertUtils.notNull(metaAccountDto.getPassword(),"交易密码不能为空!");

    AssertUtils.notNull(metaAccountDto.getGroup(),"服务器组不能为空!");

    AssertUtils.notNull(metaAccountDto.getLeverage(),"杠杆比不能为空!");

    AssertUtils.notNull(metaAccountDto.getState(),"状态不能为空!");

    AssertUtils.notNull(metaAccountDto.getLeadSource(),"Lead source不能为空!");

    AssertUtils.notNull(metaAccountDto.getEnabled(),"是否启用不能为空!");

    AssertUtils.notNull(metaAccountDto.getCurrency(),"货币不能为空!");

    AssertUtils.notNull(metaAccountDto.getCreatedAt(),"创建时间不能为空!");

    AssertUtils.notNull(metaAccountDto.getCreatedBy(),"创建者不能为空!");

    AssertUtils.notNull(metaAccountDto.getUpdatedAt(),"最后更新时间不能为空!");

    AssertUtils.notNull(metaAccountDto.getUpdatedBy(),"最后更新者不能为空!");

*/

    }




}
