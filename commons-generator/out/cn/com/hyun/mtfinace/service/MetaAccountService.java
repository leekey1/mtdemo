/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaAccountService.java
 * Description:       MetaAccountService类定义
 *        实体 MetaAccount的逻辑处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.MetaAccount
 *        cn.com.hyun.mtfinace.mapper.MetaAccountmapper.xml
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
import cn.com.hyun.mtfinace.service.MetaAccountService;
import cn.com.hyun.common.BaseService;
import cn.com.hyun.framework.utils.DateUtils;
import cn.com.hyun.configuration.ApplicationConfiguration;
import cn.com.hyun.mtfinace.validator.MetaAccountValidator;
import cn.com.hyun.mtfinace.entity.MetaAccount;
import cn.com.hyun.mtfinace.dto.MetaAccountDto;
import cn.com.hyun.mtfinace.dao.MetaAccountDao;
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
 * MetaAccountService定义.
 * <p>
 *
 *
 * @author hyun auto
 */
@Service
public class MetaAccountService extends BaseService {

    @Autowired
    MetaAccountDao metaAccountDao;


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> insertMetaAccount(MetaAccountDto metaAccountDto) {
        MetaAccountValidator.validate(metaAccountDto);
        MetaAccount metaAccount = new MetaAccount();
        BeanUtils.copyProperties(metaAccountDto,metaAccount);
        metaAccount.setCreatedAt(DateUtils.now());
        metaAccount.setCreatedBy(getEmp().getEmpName());


        int result = INTEGER_ZERO;
        result = metaAccountDao.insert(metaAccount);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> updateMetaAccount(MetaAccountDto metaAccountDto) {
        MetaAccountValidator.validate(metaAccountDto);
        MetaAccount metaAccount = new MetaAccount();
        BeanUtils.copyProperties(metaAccountDto,metaAccount);
        metaAccount.setUpdatedAt(DateUtils.now());
        metaAccount.setUpdatedBy(getEmp().getEmpName());
        int result = metaAccountDao.dynamicUpdate(metaAccount);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> deleteMetaAccount(String id) {
        int result = metaAccountDao.delete(id);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MetaAccountDto getMetaAccount(String id) {
        MetaAccountDto metaAccountDto = metaAccountDao.findOne(id);
         return metaAccountDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MetaAccountDto> listMetaAccount() {
        List<MetaAccountDto> metaAccountDtoList = metaAccountDao.findAll();
        return metaAccountDtoList;
    }


}

