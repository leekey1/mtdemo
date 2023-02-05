/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientController.java
 * Description:       ClientController类定义
 *        实体 Client的控制层处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.Client
 *        cn.com.hyun.mtfinace.mapper.Clientmapper.xml
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
import cn.com.hyun.mtfinace.service.ClientService;


import cn.com.hyun.mtfinace.entity.Client;
import cn.com.hyun.mtfinace.dto.ClientDto;
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
 * ClientController定义.
 * <p>
 *
 *
 * @author hyun auto
 */

@RestController
@Api(tags = "用户管理")
public class ClientController  extends HBaseController {

    @Autowired
    ClientService clientService;

    @ApiOperation(value = "新增用户", notes = "token必传")
    @RequestMapping(value = "/v1/insertClient" , method = RequestMethod.POST)
    public JsonResponse insertClient(@RequestBody ClientDto clientDto){
        log.info(ApplicationMessage.getByLog("insertClient","ClientDto" ,clientDto));
        Map<String,Object> result = clientService.insertClient(clientDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "修改用户", notes = "token必传")
    @RequestMapping(value = "/v1/updateClient" , method = RequestMethod.PUT)
    public JsonResponse updateClient(@RequestBody ClientDto clientDto ){
        log.info(ApplicationMessage.getByLog("updateClient","ClientDto", clientDto));
        Map<String,Object> result = clientService.updateClient(clientDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/v1/deleteClient/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteClient(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("deleteClient","主键" ,id));
        Map<String,Object> result = clientService.deleteClient(id);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "获取用户详情", notes = "token必传")
    @RequestMapping(value = "/v1/getClient/{id}" , method = RequestMethod.GET)
    public JsonResponse<ClientDto> getClient(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("getClient","主键" ,id));
        return JsonResponse.build(() -> clientService.getClient(id));
    }

    @ApiOperation(value = "获取用户列表", notes = "token必传")
    @RequestMapping(value = "/v1/listClient" , method = RequestMethod.GET)
    public JsonResponse<List<ClientDto>> listClient(){
        return JsonResponse.build(() -> clientService.listClient());
    }


}

