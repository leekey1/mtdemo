/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientValidator.java
 * Description:       校验ClientValidator类定义
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

import cn.com.hyun.mtfinace.dto.ClientDto;
import cn.com.hyun.framework.utils.AssertUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static cn.com.hyun.configuration.ApplicationMessage.*;
import java.util.Date;

 /**
  * SVU
  */
public class ClientValidator implements Validator {

    public static void validate(ClientDto clientDto) {
        new ClientValidator().validate(clientDto, null);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    AssertUtils.notNull(target);
    ClientDto clientDto = (ClientDto) target;
 /*
    AssertUtils.notNull(clientDto.getId(),"ID不能为空!");

    AssertUtils.notNull(clientDto.getFristName(),"姓不能为空!");

    AssertUtils.notNull(clientDto.getSecondName(),"名不能为空!");

    AssertUtils.notNull(clientDto.getTitle(),"性别不能为空!");

    AssertUtils.notNull(clientDto.getEmail(),"邮箱不能为空!");

    AssertUtils.notNull(clientDto.getPassword(),"密码不能为空!");

    AssertUtils.notNull(clientDto.getAdress(),"地址不能为空!");

    AssertUtils.notNull(clientDto.getCity(),"城市不能为空!");

    AssertUtils.notNull(clientDto.getCountry(),"国家不能为空!");

    AssertUtils.notNull(clientDto.getZip(),"邮编号码不能为空!");

    AssertUtils.notNull(clientDto.getPhone(),"电话不能为空!");

    AssertUtils.notNull(clientDto.getTimeZone(),"时区不能为空!");

    AssertUtils.notNull(clientDto.getCreatedAt(),"创建时间不能为空!");

    AssertUtils.notNull(clientDto.getCreatedBy(),"创建者不能为空!");

    AssertUtils.notNull(clientDto.getUpdatedAt(),"最后更新时间不能为空!");

    AssertUtils.notNull(clientDto.getUpdatedBy(),"最后更新者不能为空!");

*/

    }




}
