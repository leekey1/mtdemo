/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        DemoController.java
 * Description:       DemoController类定义
 *        实体 Demo的控制层处理
 * Dependencies:
 *        cn.Demo
 *        cn.com.hyun.demo.mapper.Demomapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2017-11-17             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.demo.controller;


import cn.com.hyun.demo.dto.DemoDto;
import cn.com.hyun.common.HBaseController;
import cn.com.hyun.common.JsonResponse;
import cn.com.hyun.configuration.ApplicationMessage;
import cn.com.hyun.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * DemoController定义.
 * <p>
 *
 *
 * @author hyun auto
 */

@RestController
@Api(tags = "demo表管理")
public class DemoController extends HBaseController {

    @Autowired
    DemoService demoService;

    @ApiOperation(value = "新增demo表", notes = "token必传")
    @RequestMapping(value = "/v1/saveDemo" , method = RequestMethod.POST)
    public JsonResponse saveDemo(@RequestBody DemoDto demoDto){
        log.info(ApplicationMessage.getByLog("saveDemo","DemoDto" ,demoDto));
        Map<String,Object> result = demoService.saveDemo(demoDto);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "修改demo表", notes = "token必传")
    @RequestMapping(value = "/v1/updateDemo" , method = RequestMethod.PUT)
    public JsonResponse updateDemo(@RequestBody DemoDto demoDto ){
        log.info(ApplicationMessage.getByLog("updateDemo","DemoDto", demoDto));
        Map<String,Object> result = demoService.updateDemo(demoDto);
         return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "删除demo表")
    @RequestMapping(value = "/v1/deleteDemo/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteDemo(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("deleteDemo","主键" ,id));
        Map<String,Object> result = demoService.deleteDemo(id);
        return JsonResponse.message(result.get("message").toString());
    }

    @ApiOperation(value = "获取demo表详情", notes = "token必传")
    @RequestMapping(value = "/v1/getDemo/{id}" , method = RequestMethod.GET)
    public JsonResponse<DemoDto> getDemo(@ApiParam(value="主键",required = true)@PathVariable String id){
        log.info(ApplicationMessage.getByLog("getDemo","主键" ,id));
        return JsonResponse.build(() -> demoService.getDemo(id));
    }

    @ApiOperation(value = "获取demo表列表", notes = "token必传")
    @RequestMapping(value = "/v1/listDemo" , method = RequestMethod.GET)
    public JsonResponse<List<DemoDto>> listDemo(){
        return JsonResponse.build(() -> demoService.listDemo());
    }


}

