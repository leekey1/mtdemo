/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientService.java
 * Description:       ClientService类定义
 *        实体 Client的逻辑处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.Client
 *        cn.com.hyun.mtfinace.mapper.Clientmapper.xml
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
import cn.com.hyun.mtfinace.service.ClientService;
import cn.com.hyun.common.BaseService;
import cn.com.hyun.framework.utils.DateUtils;
import cn.com.hyun.configuration.ApplicationConfiguration;
import cn.com.hyun.mtfinace.validator.ClientValidator;
import cn.com.hyun.mtfinace.entity.Client;
import cn.com.hyun.mtfinace.dto.ClientDto;
import cn.com.hyun.mtfinace.dao.ClientDao;
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
 * ClientService定义.
 * <p>
 *
 *
 * @author hyun auto
 */
@Service
public class ClientService extends BaseService {

    @Autowired
    ClientDao clientDao;


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> insertClient(ClientDto clientDto) {
        ClientValidator.validate(clientDto);
        Client client = new Client();
        BeanUtils.copyProperties(clientDto,client);
        client.setCreatedAt(DateUtils.now());
        client.setCreatedBy(getEmp().getEmpName());


        int result = INTEGER_ZERO;
        result = clientDao.insert(client);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> updateClient(ClientDto clientDto) {
        ClientValidator.validate(clientDto);
        Client client = new Client();
        BeanUtils.copyProperties(clientDto,client);
        client.setUpdatedAt(DateUtils.now());
        client.setUpdatedBy(getEmp().getEmpName());
        int result = clientDao.dynamicUpdate(client);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> deleteClient(String id) {
        int result = clientDao.delete(id);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ClientDto getClient(String id) {
        ClientDto clientDto = clientDao.findOne(id);
         return clientDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ClientDto> listClient() {
        List<ClientDto> clientDtoList = clientDao.findAll();
        return clientDtoList;
    }


}

