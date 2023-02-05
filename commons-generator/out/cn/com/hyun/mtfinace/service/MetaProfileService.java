/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaProfileService.java
 * Description:       MetaProfileService类定义
 *        实体 MetaProfile的逻辑处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.MetaProfile
 *        cn.com.hyun.mtfinace.mapper.MetaProfilemapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2023-02-05             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.service;

import java.util.List;
import java.util.Map;
import cn.com.hyun.common.auth.CurrentEmployee;
import cn.com.hyun.mtfinace.service.MetaProfileService;
import cn.com.hyun.common.BaseService;
import cn.com.hyun.framework.utils.DateUtils;
import cn.com.hyun.configuration.ApplicationConfiguration;
import cn.com.hyun.mtfinace.validator.MetaProfileValidator;
import cn.com.hyun.mtfinace.entity.MetaProfile;
import cn.com.hyun.mtfinace.dto.MetaProfileDto;
import cn.com.hyun.mtfinace.dao.MetaProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * MetaProfileService定义.
 * <p>
 *
 *
 * @author hyun auto
 */
@Service
public class MetaProfileService extends BaseService {

    @Autowired
    MetaProfileDao metaProfileDao;


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> insertMetaProfile(MetaProfileDto metaProfileDto) {
        MetaProfileValidator.validate(metaProfileDto);
        MetaProfile metaProfile = new MetaProfile();
        BeanUtils.copyProperties(metaProfileDto,metaProfile);
        metaProfile.setCreatedAt(DateUtils.now());
        metaProfile.setCreatedBy(getEmp().getEmpName());


        int result = INTEGER_ZERO;
        result = metaProfileDao.insert(metaProfile);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> updateMetaProfile(MetaProfileDto metaProfileDto) {
        MetaProfileValidator.validate(metaProfileDto);
        MetaProfile metaProfile = new MetaProfile();
        BeanUtils.copyProperties(metaProfileDto,metaProfile);
        metaProfile.setUpdatedAt(DateUtils.now());
        metaProfile.setUpdatedBy(getEmp().getEmpName());
        int result = metaProfileDao.dynamicUpdate(metaProfile);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> deleteMetaProfile(String id) {
        int result = metaProfileDao.delete(id);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MetaProfileDto getMetaProfile(String id) {
        MetaProfileDto metaProfileDto = metaProfileDao.findOne(id);
         return metaProfileDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MetaProfileDto> listMetaProfile() {
        List<MetaProfileDto> metaProfileDtoList = metaProfileDao.findAll();
        return metaProfileDtoList;
    }


}

