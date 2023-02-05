/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        Demo.java
 * Description:       实体Demo类定义
 *
 * Dependencies:
 *
 * History:
 *     Date                   Modifier              Log
 *     2017-11-17             hyun auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.demo.validator;



import cn.com.hyun.demo.dto.DemoDto;
import cn.com.hyun.framework.utils.AssertUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * SVU
 */
public class DemoValidator implements Validator {

   public static void validate(DemoDto demoDto) {
       new DemoValidator().validate(demoDto, null);
   }

   @Override
   public boolean supports(Class<?> clazz) {
       return DemoDto.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {

   AssertUtils.notNull(target);
   DemoDto demoDto = (DemoDto) target;

//    AssertUtils.notNull(demoDto.getId(),"ID不能为空!");
//
//    AssertUtils.notNull(demoDto.getLoanId(),"进件ID不能为空!");
//
//    AssertUtils.notNull(demoDto.getDemoOpprator(),"操作人不能为空!");
//
//    AssertUtils.notNull(demoDto.getDemoOppratorType(),"操作类型不能为空!");
//
//    AssertUtils.notNull(demoDto.getDemoOppratorTime(),"操作时间不能为空!");
//
//    AssertUtils.notNull(demoDto.getDemoMoney(),"金额不能为空!");
//
//    AssertUtils.notNull(demoDto.getDemoRate(),"比列不能为空!");
//
//    AssertUtils.notNull(demoDto.getDemoMonth(),"借款月不能为空!");
//
//    AssertUtils.notNull(demoDto.getCreatedAt(),"创建时间不能为空!");
//
//    AssertUtils.notNull(demoDto.getCreatedBy(),"创建者不能为空!");
//
//    AssertUtils.notNull(demoDto.getUpdatedAt(),"最后更新时间不能为空!");
//
//    AssertUtils.notNull(demoDto.getUpdatedBy(),"最后更新者不能为空!");
//
//    AssertUtils.notNull(demoDto.getVersion(),"版本不能为空!");


   }




}
