/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientBalenceHistoryService.java
 * Description:       ClientBalenceHistoryService类定义
 *        实体 ClientBalenceHistory的逻辑处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.ClientBalenceHistory
 *        cn.com.hyun.mtfinace.mapper.ClientBalenceHistorymapper.xml
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
import cn.com.hyun.mtfinace.service.ClientBalenceHistoryService;
import cn.com.hyun.common.BaseService;
import cn.com.hyun.framework.utils.DateUtils;
import cn.com.hyun.configuration.ApplicationConfiguration;
import cn.com.hyun.mtfinace.validator.ClientBalenceHistoryValidator;
import cn.com.hyun.mtfinace.entity.ClientBalenceHistory;
import cn.com.hyun.mtfinace.dto.ClientBalenceHistoryDto;
import cn.com.hyun.mtfinace.dao.ClientBalenceHistoryDao;
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
 * ClientBalenceHistoryService定义.
 * <p>
 *
 *
 * @author hyun auto
 */
@Service
public class ClientBalenceHistoryService extends BaseService {

    @Autowired
    ClientBalenceHistoryDao clientBalenceHistoryDao;


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> insertClientBalenceHistory(ClientBalenceHistoryDto clientBalenceHistoryDto) {
        ClientBalenceHistoryValidator.validate(clientBalenceHistoryDto);
        ClientBalenceHistory clientBalenceHistory = new ClientBalenceHistory();
        BeanUtils.copyProperties(clientBalenceHistoryDto,clientBalenceHistory);
        clientBalenceHistory.setCreatedAt(DateUtils.now());
        clientBalenceHistory.setCreatedBy(getEmp().getEmpName());


        int result = INTEGER_ZERO;
        result = clientBalenceHistoryDao.insert(clientBalenceHistory);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> updateClientBalenceHistory(ClientBalenceHistoryDto clientBalenceHistoryDto) {
        ClientBalenceHistoryValidator.validate(clientBalenceHistoryDto);
        ClientBalenceHistory clientBalenceHistory = new ClientBalenceHistory();
        BeanUtils.copyProperties(clientBalenceHistoryDto,clientBalenceHistory);
        clientBalenceHistory.setUpdatedAt(DateUtils.now());
        clientBalenceHistory.setUpdatedBy(getEmp().getEmpName());
        int result = clientBalenceHistoryDao.dynamicUpdate(clientBalenceHistory);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> deleteClientBalenceHistory(String id) {
        int result = clientBalenceHistoryDao.delete(id);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ClientBalenceHistoryDto getClientBalenceHistory(String id) {
        ClientBalenceHistoryDto clientBalenceHistoryDto = clientBalenceHistoryDao.findOne(id);
         return clientBalenceHistoryDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ClientBalenceHistoryDto> listClientBalenceHistory() {
        List<ClientBalenceHistoryDto> clientBalenceHistoryDtoList = clientBalenceHistoryDao.findAll();
        return clientBalenceHistoryDtoList;
    }


}

