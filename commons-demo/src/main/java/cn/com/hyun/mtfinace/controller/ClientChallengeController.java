/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientChallengeController.java
 * Description:       ClientChallengeController类定义
 *        实体 ClientChallenge的控制层处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.ClientChallenge
 *        cn.com.hyun.mtfinace.mapper.ClientChallengemapper.xml
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
import cn.com.hyun.mtfinace.service.ClientChallengeService;


import cn.com.hyun.mtfinace.entity.ClientChallenge;
import cn.com.hyun.mtfinace.dto.ClientChallengeDto;
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
 * ClientChallengeController定义.
 * <p>
 *
 *
 * @author hyun auto
 */

@RestController
@Api(tags = "客户挑战管理")
public class ClientChallengeController  extends HBaseController {

    @Autowired
    ClientChallengeService clientChallengeService;

    @ApiOperation(value = "新增客户挑战", notes = "token必传")
    @RequestMapping(value = "/v1/insertClientChallenge" , method = RequestMethod.POST)
    public JsonResponse insertClientChallenge(@RequestBody ClientChallengeDto clientChallengeDto){
        log.info(ApplicationMessage.getByLog("insertClientChallenge","ClientChallengeDto" ,clientChallengeDto));
        Map<String,Object> result = clientChallengeService.insertClientChallenge(clientChallengeDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "修改客户挑战", notes = "token必传")
    @RequestMapping(value = "/v1/updateClientChallenge" , method = RequestMethod.PUT)
    public JsonResponse updateClientChallenge(@RequestBody ClientChallengeDto clientChallengeDto ){
        log.info(ApplicationMessage.getByLog("updateClientChallenge","ClientChallengeDto", clientChallengeDto));
        Map<String,Object> result = clientChallengeService.updateClientChallenge(clientChallengeDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "删除客户挑战")
    @RequestMapping(value = "/v1/deleteClientChallenge/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteClientChallenge(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("deleteClientChallenge","主键" ,id));
        Map<String,Object> result = clientChallengeService.deleteClientChallenge(id);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "获取客户挑战详情", notes = "token必传")
    @RequestMapping(value = "/v1/getClientChallenge/{id}" , method = RequestMethod.GET)
    public JsonResponse<ClientChallengeDto> getClientChallenge(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("getClientChallenge","主键" ,id));
        return JsonResponse.build(() -> clientChallengeService.getClientChallenge(id));
    }

    @ApiOperation(value = "获取客户挑战列表", notes = "token必传")
    @RequestMapping(value = "/v1/listClientChallenge" , method = RequestMethod.GET)
    public JsonResponse<List<ClientChallengeDto>> listClientChallenge(){
        return JsonResponse.build(() -> clientChallengeService.listClientChallenge());
    }


}

