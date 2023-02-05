/* ---------------------------------------------------------------------------------------------------
 * Copyright (C) 2014-2015 hyun.com.cn
 *
 * Title:        DemoDao.java
 * Description:       DemoDao类定义
 *        实体 Demo的持久化处理
 * Dependencies:
 *        cn.Demo
 *        cn.com.hyun.demo.mapper.Demomapper.xml
 * History:
 *     Date                 Modifier             Log
 *     2017-11-17             hyun auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.hyun.demo.dao;


import cn.com.hyun.demo.dto.DemoDto;
import cn.com.hyun.demo.entity.Demo;

import java.util.List;


/**
 * DemoDao定义.
 * <p>
 * 数据访问层<demo表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author hyun auto
 */
public interface DemoDao {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  id
     * @return DemoDto
     */
	public DemoDto findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<DemoDto>
     */
	public List<DemoDto> findAll();
    
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  demo
     * @return 插入成功标志
     */
	public int insert(Demo demo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param   demo
     * @return 更新成功标志
     */
	public int dynamicUpdate(Demo demo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param   demo
     * @return 更新成功标志
     */
	public int update(Demo demo);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param  id
     * @return 删除成功标志
     */
	public int delete(String id);

}

