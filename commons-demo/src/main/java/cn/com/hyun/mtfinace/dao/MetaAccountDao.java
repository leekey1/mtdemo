/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaAccountDao.java
 * Description:       MetaAccountDao类定义
 *        实体 MetaAccount的持久化处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.MetaAccount
 *        cn.com.hyun.mtfinace.mapper.MetaAccountmapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2023-02-05             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.hyun.mtfinace.entity.MetaAccount;

import cn.com.hyun.mtfinace.dto.MetaAccountDto;


/**
 * MetaAccountDao定义.
 * <p>
 * 数据访问层<交易账户>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author hyun auto
 */
public interface MetaAccountDao {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  id
     * @return MetaAccountDto
     */
	public MetaAccountDto findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<MetaAccountDto>
     */
	public List<MetaAccountDto> findAll();
    
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  metaAccount
     * @return 插入成功标志
     */
	public int insert(MetaAccount metaAccount);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param   metaAccount
     * @return 更新成功标志
     */
	public int dynamicUpdate(MetaAccount metaAccount);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param   metaAccount
     * @return 更新成功标志
     */
	public int update(MetaAccount metaAccount);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param  id
     * @return 删除成功标志
     */
	public int delete(String id);

}

