package cn.com.hyun.framework.page.orderbyhelper.sqlsource;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;
import java.util.HashMap;

public class OrderByProviderSqlSource implements SqlSource, OrderBySqlSource {
    private SqlSourceBuilder sqlSourceParser;
    private Class<?> providerType;
    private Method providerMethod;
    private Boolean providerTakesParameterObject;
    private SqlSource original;

    public OrderByProviderSqlSource(ProviderSqlSource provider) {
        MetaObject metaObject = SystemMetaObject.forObject(provider);
        this.sqlSourceParser = (SqlSourceBuilder) metaObject.getValue("sqlSourceParser");
        this.providerType = (Class<?>) metaObject.getValue("providerType");
        this.providerMethod = (Method) metaObject.getValue("providerMethod");
        this.providerTakesParameterObject = (Boolean) metaObject.getValue("providerTakesParameterObject");
        this.original = provider;
    }

    public BoundSql getBoundSql(Object parameterObject) {
        SqlSource sqlSource = createSqlSource(parameterObject);
        return sqlSource.getBoundSql(parameterObject);
    }

    private SqlSource createSqlSource(Object parameterObject) {
        try {
            String sql;
            if (providerTakesParameterObject) {
                sql = (String) providerMethod.invoke(providerType.newInstance(), parameterObject);
            } else {
                sql = (String) providerMethod.invoke(providerType.newInstance());
            }
            Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
            StaticSqlSource sqlSource = (StaticSqlSource) sqlSourceParser.parse(sql, parameterType, new HashMap<String, Object>());
            return new OrderByStaticSqlSource(sqlSource);
        } catch (Exception e) {
            throw new BuilderException("Error invoking SqlProvider method ("
                    + providerType.getName() + "." + providerMethod.getName()
                    + ").  Cause: " + e, e);
        }
    }

    public SqlSource getOriginal() {
        return original;
    }

}