package cn.com.hyun.configuration;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import cn.com.hyun.framework.page.pagehelper.PageHelper;
import cn.com.hyun.framework.utils.ArrayUtils;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by helloworld on 2017/2/16.
 */
@Configuration
@MapperScan(sqlSessionFactoryRef = "sessionFactory", basePackages = {"cn.com.hyun.*.dao"})
@Slf4j
public class DatabaseConfiguration {
//    @Bean(initMethod = "init", destroyMethod = "close")
//    @ConfigurationProperties(prefix = "spring.druid", exceptionIfInvalid = false)
//    public DataSource datasource() {
//        return new DruidDataSource();
//    }
    @Bean
    @ConfigurationProperties(prefix = "spring.druid")
    public DataSource datasource() {
        return new DruidDataSource();
    }
//
//    @Value("${spring.druid.url}")
//    private String url;
//    @Value("${spring.druid.username}")
//    private String username;
//    @Value("${spring.druid.password}")
//    private String password;
//    @Value("${spring.druid.driverClassName}")
//    private String driverClassName;
//    @Value("${spring.druid.maxActive}")
//    private String maxActive;
//    @Value("${spring.druid.minIdle}")
//    private String minIdle;
//    @Value("${spring.druid.initialSize}")
//    private String initialSize;
//    @Value("${spring.druid.maxWait}")
//    private String maxWait;
//    @Value("${spring.druid.timeBetweenEvictionRunsMillis}")
//    private String timeBetweenEvictionRunsMillis;
//    @Value("${spring.druid.minEvictableIdleTimeMillis}")
//    private String minEvictableIdleTimeMillis;
//    @Value("${spring.druid.testWhileIdle}")
//    private String testWhileIdle;
//    @Value("${spring.druid.testWhileIdle}")
//    private String testOnBorrow;
//    @Value("${spring.druid.testOnBorrow}")
//    private String testOnReturn;
//    @Value("${spring.druid.filters}")
//    private String filters;
//
//    @Bean(name = "dataSource")
//    @Primary
//    public DataSource datasource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setMaxActive(Integer.parseInt(maxActive));
//        dataSource.setMinIdle(Integer.parseInt(minIdle));
//        dataSource.setInitialSize(Integer.parseInt(initialSize));
//        dataSource.setMaxWait(Long.parseLong( maxWait));
//        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
//        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(minEvictableIdleTimeMillis));
//        dataSource.setTestWhileIdle(testWhileIdle.trim().equals("true"));
//        dataSource.setTestOnBorrow(testOnBorrow.trim().equals("true"));
//        dataSource.setTestOnReturn(testOnReturn.trim().equals("true"));
//        dataSource.setValidationQuery("select 1 from dual");
//        dataSource.setPoolPreparedStatements(true);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
//
//        try {
//             /*
//            配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
//            # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
//            # 监控统计用的filter:stat
//            # 日志用的filter:log4j
//            # 防御sql注入的filter:wall
//            spring.datasource.filters=stat,log4j,wall
//            */
//            dataSource.setFilters(filters);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }

    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {



        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(datasource());

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(500);
        sessionFactoryBean.setConfiguration(configuration);

        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:cn/com/hyun/**/mapper/*.xml");
            sessionFactoryBean.setMapperLocations(resources);
            PageHelper pageHelper = new PageHelper();
            sessionFactoryBean.setPlugins(ArrayUtils.toArray(pageHelper));
        } catch (IOException e) {
            log.error("初始化sessionFactory异常:{}", ExceptionUtils.getStackTrace(e));
        }

        return sessionFactoryBean.getObject();
    }


    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(datasource());
        dataSourceTransactionManager.setDefaultTimeout(-1);
        return dataSourceTransactionManager;
    }


    @Bean
    public WebStatFilter webStatFilter() {
        return new WebStatFilter();
    }

    @Bean
    public FilterRegistrationBean webStatFilterBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "/druid/*,/hystrix/*,*.ico,*.stream,*.js,*.css");
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
}
