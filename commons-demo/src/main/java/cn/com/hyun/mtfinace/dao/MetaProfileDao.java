/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        MetaProfileDao.java
 * Description:       MetaProfileDao类定义
 *        实体 MetaProfile的持久化处理
 * Dependencies:
 *        cn.com.hyun.mtfinace.entity.MetaProfile
 *        cn.com.hyun.mtfinace.mapper.MetaProfilemapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2023-02-05             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.mtfinace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.hyun.mtfinace.entity.MetaProfile;

import cn.com.hyun.mtfinace.dto.MetaProfileDto;


/**
 * MetaProfileDao定义.
 * <p>
 * 数据访问层<Meta服务器信息>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author hyun auto
 */
public interface MetaProfileDao {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  id
     * @return MetaProfileDto
     */
	public MetaProfileDto findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<MetaProfileDto>
     */
	public List<MetaProfileDto> findAll();
    
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  metaProfile
     * @return 插入成功标志
     */
	public int insert(MetaProfile metaProfile);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param   metaProfile
     * @return 更新成功标志
     */
	public int dynamicUpdate(MetaProfile metaProfile);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param   metaProfile
     * @return 更新成功标志
     */
	public int update(MetaProfile metaProfile);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param  id
     * @return 删除成功标志
     */
	public int delete(String id);

}

