/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        DemoService.java
 * Description:       DemoService类定义
 *        实体 Demo的逻辑处理
 * Dependencies:
 *        cn.Demo
 *        cn.com.hyun.demo.mapper.Demomapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2017-11-17             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.demo.service;


import cn.com.hyun.demo.dto.DemoDto;
import cn.com.hyun.common.BaseService;
import cn.com.hyun.configuration.ApplicationConfiguration;
import cn.com.hyun.demo.dao.DemoDao;
import cn.com.hyun.demo.entity.Demo;
import cn.com.hyun.demo.validator.DemoValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;

/**
 * DemoService定义.
 * <p>
 *
 *
 * @author hyun auto
 */
@Service
public class DemoService extends BaseService {

    @Autowired
    DemoDao demoDao;



    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<DemoDto> DemoDtoTest() {
    List<DemoDto> demoDtoList = demoDao.findAll();
        return demoDtoList;
    }

    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> saveDemo(DemoDto demoDto) {
        DemoValidator.validate(demoDto);
        Demo demo = new Demo();
        BeanUtils.copyProperties(demoDto,demo);
        demo.setCreatedBy(getEmp().getEmpName());
        int result = INTEGER_ZERO;
        result = demoDao.insert(demo);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> updateDemo(DemoDto demoDto) {
        DemoValidator.validate(demoDto);
        Demo demo = new Demo();
        BeanUtils.copyProperties(demoDto,demo);
        //demo.setCreatedBy(getEmp().getEmpName());
        int result = demoDao.dynamicUpdate(demo);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> deleteDemo(String id) {
        int result = demoDao.delete(id);
        if (result > INTEGER_ZERO) {
        return successState();
        } else {
            return failState();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public DemoDto getDemo(String id) {
        DemoDto demoDto = demoDao.findOne(id);
        return demoDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<DemoDto> listDemo() {
        List<DemoDto> demoDtoList = demoDao.findAll();
        return demoDtoList;
    }


}

