/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaProfileController.java
 * Description:       MetaProfileController类定义
 *        实体 MetaProfile的控制层处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.MetaProfile
 *        cn.com.hyun.mtfinace.mapper.MetaProfilemapper.xml
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
import cn.com.hyun.mtfinace.service.MetaProfileService;


import cn.com.hyun.mtfinace.entity.MetaProfile;
import cn.com.hyun.mtfinace.dto.MetaProfileDto;
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
 * MetaProfileController定义.
 * <p>
 *
 *
 * @author hyun auto
 */

@RestController
@Api(tags = "Meta服务器信息管理")
public class MetaProfileController  extends HBaseController {

    @Autowired
    MetaProfileService metaProfileService;

    @ApiOperation(value = "新增Meta服务器信息", notes = "token必传")
    @RequestMapping(value = "/v1/insertMetaProfile" , method = RequestMethod.POST)
    public JsonResponse insertMetaProfile(@RequestBody MetaProfileDto metaProfileDto){
        log.info(ApplicationMessage.getByLog("insertMetaProfile","MetaProfileDto" ,metaProfileDto));
        Map<String,Object> result = metaProfileService.insertMetaProfile(metaProfileDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "修改Meta服务器信息", notes = "token必传")
    @RequestMapping(value = "/v1/updateMetaProfile" , method = RequestMethod.PUT)
    public JsonResponse updateMetaProfile(@RequestBody MetaProfileDto metaProfileDto ){
        log.info(ApplicationMessage.getByLog("updateMetaProfile","MetaProfileDto", metaProfileDto));
        Map<String,Object> result = metaProfileService.updateMetaProfile(metaProfileDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "删除Meta服务器信息")
    @RequestMapping(value = "/v1/deleteMetaProfile/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteMetaProfile(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("deleteMetaProfile","主键" ,id));
        Map<String,Object> result = metaProfileService.deleteMetaProfile(id);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "获取Meta服务器信息详情", notes = "token必传")
    @RequestMapping(value = "/v1/getMetaProfile/{id}" , method = RequestMethod.GET)
    public JsonResponse<MetaProfileDto> getMetaProfile(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("getMetaProfile","主键" ,id));
        return JsonResponse.build(() -> metaProfileService.getMetaProfile(id));
    }

    @ApiOperation(value = "获取Meta服务器信息列表", notes = "token必传")
    @RequestMapping(value = "/v1/listMetaProfile" , method = RequestMethod.GET)
    public JsonResponse<List<MetaProfileDto>> listMetaProfile(){
        return JsonResponse.build(() -> metaProfileService.listMetaProfile());
    }


}

