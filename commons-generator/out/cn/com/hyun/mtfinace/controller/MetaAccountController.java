/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaAccountController.java
 * Description:       MetaAccountController类定义
 *        实体 MetaAccount的控制层处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.MetaAccount
 *        cn.com.hyun.mtfinace.mapper.MetaAccountmapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2023-02-05             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.controller;

import java.util.List;


import cn.com.hyun.configuration.ApplicationMessage;
import cn.com.hyun.common.HBaseController;

import cn.com.hyun.framework.page.pagehelper.PageHelper;
import cn.com.hyun.framework.page.pagehelper.PageInfo;
import cn.com.hyun.framework.web.controller.BaseController;
import cn.com.hyun.common.JsonResponse;

import cn.com.hyun.framework.web.util.HttpContextUtils;
import cn.com.hyun.mtfinace.service.MetaAccountService;


import cn.com.hyun.mtfinace.entity.MetaAccount;
import cn.com.hyun.mtfinace.dto.MetaAccountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * MetaAccountController定义.
 * <p>
 *
 *
 * @author hyun auto
 */

@RestController
@Api(tags = "交易账户管理")
public class MetaAccountController  extends HBaseController {

    @Autowired
    MetaAccountService metaAccountService;

    @ApiOperation(value = "新增交易账户", notes = "token必传")
    @RequestMapping(value = "/v1/insertMetaAccount" , method = RequestMethod.POST)
    public JsonResponse insertMetaAccount(@RequestBody MetaAccountDto metaAccountDto){
        log.info(ApplicationMessage.getByLog("insertMetaAccount","MetaAccountDto" ,metaAccountDto));
        Map<String,Object> result = metaAccountService.insertMetaAccount(metaAccountDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "修改交易账户", notes = "token必传")
    @RequestMapping(value = "/v1/updateMetaAccount" , method = RequestMethod.PUT)
    public JsonResponse updateMetaAccount(@RequestBody MetaAccountDto metaAccountDto ){
        log.info(ApplicationMessage.getByLog("updateMetaAccount","MetaAccountDto", metaAccountDto));
        Map<String,Object> result = metaAccountService.updateMetaAccount(metaAccountDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "删除交易账户")
    @RequestMapping(value = "/v1/deleteMetaAccount/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteMetaAccount(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("deleteMetaAccount","主键" ,id));
        Map<String,Object> result = metaAccountService.deleteMetaAccount(id);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "获取交易账户详情", notes = "token必传")
    @RequestMapping(value = "/v1/getMetaAccount/{id}" , method = RequestMethod.GET)
    public JsonResponse<MetaAccountDto> getMetaAccount(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("getMetaAccount","主键" ,id));
        return JsonResponse.build(() -> metaAccountService.getMetaAccount(id));
    }

    @ApiOperation(value = "获取交易账户列表", notes = "token必传")
    @RequestMapping(value = "/v1/listMetaAccount" , method = RequestMethod.GET)
    public JsonResponse<List<MetaAccountDto>> listMetaAccount(){
        return JsonResponse.build(() -> metaAccountService.listMetaAccount());
    }


}

