/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaProfileValidator.java
 * Description:       校验MetaProfileValidator类定义
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

import cn.com.hyun.mtfinace.dto.MetaProfileDto;
import cn.com.hyun.framework.utils.AssertUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static cn.com.hyun.configuration.ApplicationMessage.*;
import java.util.Date;

 /**
  * SVU
  */
public class MetaProfileValidator implements Validator {

    public static void validate(MetaProfileDto metaProfileDto) {
        new MetaProfileValidator().validate(metaProfileDto, null);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MetaProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    AssertUtils.notNull(target);
    MetaProfileDto metaProfileDto = (MetaProfileDto) target;
 /*
    AssertUtils.notNull(metaProfileDto.getId(),"ID不能为空!");

    AssertUtils.notNull(metaProfileDto.getBroker(),"服务详细名不能为空!");

    AssertUtils.notNull(metaProfileDto.getServer(),"服务名不能为空!");

    AssertUtils.notNull(metaProfileDto.getApiProfileId(),"Profile ID不能为空!");

    AssertUtils.notNull(metaProfileDto.getApiProfileName(),"profile名不能为空!");

    AssertUtils.notNull(metaProfileDto.getBrokerTimezone(),"时区不能为空!");

    AssertUtils.notNull(metaProfileDto.getBrokerDstSwitchTimezone(),"服务时区不能为空!");

    AssertUtils.notNull(metaProfileDto.getType(),"类型不能为空!");

    AssertUtils.notNull(metaProfileDto.getVersion(),"版本不能为空!");

    AssertUtils.notNull(metaProfileDto.getCreatedAt(),"创建时间不能为空!");

    AssertUtils.notNull(metaProfileDto.getCreatedBy(),"创建者不能为空!");

    AssertUtils.notNull(metaProfileDto.getUpdatedAt(),"最后更新时间不能为空!");

    AssertUtils.notNull(metaProfileDto.getUpdatedBy(),"最后更新者不能为空!");

*/

    }




}
