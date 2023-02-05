/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientBalenceHistoryController.java
 * Description:       ClientBalenceHistoryController类定义
 *        实体 ClientBalenceHistory的控制层处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.ClientBalenceHistory
 *        cn.com.hyun.mtfinace.mapper.ClientBalenceHistorymapper.xml
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
import cn.com.hyun.mtfinace.service.ClientBalenceHistoryService;


import cn.com.hyun.mtfinace.entity.ClientBalenceHistory;
import cn.com.hyun.mtfinace.dto.ClientBalenceHistoryDto;
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
 * ClientBalenceHistoryController定义.
 * <p>
 *
 *
 * @author hyun auto
 */

@RestController
@Api(tags = "交易账户余额充值提现信息管理")
public class ClientBalenceHistoryController  extends HBaseController {

    @Autowired
    ClientBalenceHistoryService clientBalenceHistoryService;

    @ApiOperation(value = "新增交易账户余额充值提现信息", notes = "token必传")
    @RequestMapping(value = "/v1/insertClientBalenceHistory" , method = RequestMethod.POST)
    public JsonResponse insertClientBalenceHistory(@RequestBody ClientBalenceHistoryDto clientBalenceHistoryDto){
        log.info(ApplicationMessage.getByLog("insertClientBalenceHistory","ClientBalenceHistoryDto" ,clientBalenceHistoryDto));
        Map<String,Object> result = clientBalenceHistoryService.insertClientBalenceHistory(clientBalenceHistoryDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "修改交易账户余额充值提现信息", notes = "token必传")
    @RequestMapping(value = "/v1/updateClientBalenceHistory" , method = RequestMethod.PUT)
    public JsonResponse updateClientBalenceHistory(@RequestBody ClientBalenceHistoryDto clientBalenceHistoryDto ){
        log.info(ApplicationMessage.getByLog("updateClientBalenceHistory","ClientBalenceHistoryDto", clientBalenceHistoryDto));
        Map<String,Object> result = clientBalenceHistoryService.updateClientBalenceHistory(clientBalenceHistoryDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "删除交易账户余额充值提现信息")
    @RequestMapping(value = "/v1/deleteClientBalenceHistory/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteClientBalenceHistory(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("deleteClientBalenceHistory","主键" ,id));
        Map<String,Object> result = clientBalenceHistoryService.deleteClientBalenceHistory(id);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "获取交易账户余额充值提现信息详情", notes = "token必传")
    @RequestMapping(value = "/v1/getClientBalenceHistory/{id}" , method = RequestMethod.GET)
    public JsonResponse<ClientBalenceHistoryDto> getClientBalenceHistory(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("getClientBalenceHistory","主键" ,id));
        return JsonResponse.build(() -> clientBalenceHistoryService.getClientBalenceHistory(id));
    }

    @ApiOperation(value = "获取交易账户余额充值提现信息列表", notes = "token必传")
    @RequestMapping(value = "/v1/listClientBalenceHistory" , method = RequestMethod.GET)
    public JsonResponse<List<ClientBalenceHistoryDto>> listClientBalenceHistory(){
        return JsonResponse.build(() -> clientBalenceHistoryService.listClientBalenceHistory());
    }


}

