/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        ClientDao.java
 * Description:       ClientDao类定义
 *        实体 Client的持久化处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.Client
 *        cn.com.hyun.mtfinace.mapper.Clientmapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2023-02-05             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.hyun.mtfinace.entity.Client;

import cn.com.hyun.mtfinace.dto.ClientDto;


/**
 * ClientDao定义.
 * <p>
 * 数据访问层<用户>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author hyun auto
 */
public interface ClientDao {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  id
     * @return ClientDto
     */
	public ClientDto findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<ClientDto>
     */
	public List<ClientDto> findAll();
    
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  client
     * @return 插入成功标志
     */
	public int insert(Client client);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param   client
     * @return 更新成功标志
     */
	public int dynamicUpdate(Client client);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param   client
     * @return 更新成功标志
     */
	public int update(Client client);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param  id
     * @return 删除成功标志
     */
	public int delete(String id);

}

