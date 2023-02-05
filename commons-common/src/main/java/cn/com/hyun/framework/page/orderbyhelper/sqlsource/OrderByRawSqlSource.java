package cn.com.hyun.framework.page.orderbyhelper.sqlsource;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

public class OrderByRawSqlSource implements SqlSource, OrderBySqlSource {

    private final SqlSource sqlSource;
    private SqlSource original;

    public OrderByRawSqlSource(RawSqlSource sqlSource) {
        MetaObject metaObject = SystemMetaObject.forObject(sqlSource);
        this.sqlSource = new OrderByStaticSqlSource((StaticSqlSource) metaObject.getValue("sqlSource"));
        this.original = sqlSource;
    }

    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    public SqlSource getOriginal() {
        return original;
    }

}