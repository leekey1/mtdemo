package cn.com.hyun.framework.page.pagehelper.parser.impl;

import cn.com.hyun.framework.page.pagehelper.Page;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.Map;

public class H2Parser extends AbstractParser {
    @Override
    public String getPageSql(String sql) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 20);
        sqlBuilder.append(sql);
        sqlBuilder.append(" limit ? offset ?");
        return sqlBuilder.toString();
    }

    @Override
    public Map<String, Object> setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page<?> page) {
        Map<String, Object> paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
        paramMap.put(PAGEPARAMETER_FIRST, page.getPageSize());
        paramMap.put(PAGEPARAMETER_SECOND, page.getStartRow());
        return paramMap;
    }
}