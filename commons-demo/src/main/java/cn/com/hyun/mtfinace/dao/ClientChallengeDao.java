/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientChallengeDao.java
 * Description:       ClientChallengeDao类定义
 *        实体 ClientChallenge的持久化处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.ClientChallenge
 *        cn.com.hyun.mtfinace.mapper.ClientChallengemapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2023-02-05             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.hyun.mtfinace.entity.ClientChallenge;

import cn.com.hyun.mtfinace.dto.ClientChallengeDto;


/**
 * ClientChallengeDao定义.
 * <p>
 * 数据访问层<客户挑战>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author hyun auto
 */
public interface ClientChallengeDao {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  id
     * @return ClientChallengeDto
     */
	public ClientChallengeDto findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<ClientChallengeDto>
     */
	public List<ClientChallengeDto> findAll();
    
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  clientChallenge
     * @return 插入成功标志
     */
	public int insert(ClientChallenge clientChallenge);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param   clientChallenge
     * @return 更新成功标志
     */
	public int dynamicUpdate(ClientChallenge clientChallenge);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param   clientChallenge
     * @return 更新成功标志
     */
	public int update(ClientChallenge clientChallenge);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param  id
     * @return 删除成功标志
     */
	public int delete(String id);

}

