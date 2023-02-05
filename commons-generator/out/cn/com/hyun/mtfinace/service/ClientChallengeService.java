/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientChallengeService.java
 * Description:       ClientChallengeService类定义
 *        实体 ClientChallenge的逻辑处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.ClientChallenge
 *        cn.com.hyun.mtfinace.mapper.ClientChallengemapper.xml
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
import cn.com.hyun.mtfinace.service.ClientChallengeService;
import cn.com.hyun.common.BaseService;
import cn.com.hyun.framework.utils.DateUtils;
import cn.com.hyun.configuration.ApplicationConfiguration;
import cn.com.hyun.mtfinace.validator.ClientChallengeValidator;
import cn.com.hyun.mtfinace.entity.ClientChallenge;
import cn.com.hyun.mtfinace.dto.ClientChallengeDto;
import cn.com.hyun.mtfinace.dao.ClientChallengeDao;
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
 * ClientChallengeService定义.
 * <p>
 *
 *
 * @author hyun auto
 */
@Service
public class ClientChallengeService extends BaseService {

    @Autowired
    ClientChallengeDao clientChallengeDao;


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> insertClientChallenge(ClientChallengeDto clientChallengeDto) {
        ClientChallengeValidator.validate(clientChallengeDto);
        ClientChallenge clientChallenge = new ClientChallenge();
        BeanUtils.copyProperties(clientChallengeDto,clientChallenge);
        clientChallenge.setCreatedAt(DateUtils.now());
        clientChallenge.setCreatedBy(getEmp().getEmpName());


        int result = INTEGER_ZERO;
        result = clientChallengeDao.insert(clientChallenge);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> updateClientChallenge(ClientChallengeDto clientChallengeDto) {
        ClientChallengeValidator.validate(clientChallengeDto);
        ClientChallenge clientChallenge = new ClientChallenge();
        BeanUtils.copyProperties(clientChallengeDto,clientChallenge);
        clientChallenge.setUpdatedAt(DateUtils.now());
        clientChallenge.setUpdatedBy(getEmp().getEmpName());
        int result = clientChallengeDao.dynamicUpdate(clientChallenge);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }


    @Transactional(value = ApplicationConfiguration.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map<String, Object> deleteClientChallenge(String id) {
        int result = clientChallengeDao.delete(id);
        if (result > INTEGER_ZERO) {
            return successState();
        } else {
            return failState();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ClientChallengeDto getClientChallenge(String id) {
        ClientChallengeDto clientChallengeDto = clientChallengeDao.findOne(id);
         return clientChallengeDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ClientChallengeDto> listClientChallenge() {
        List<ClientChallengeDto> clientChallengeDtoList = clientChallengeDao.findAll();
        return clientChallengeDtoList;
    }


}

