package cn.com.hyun.configuration;


import cn.com.hyun.common.auth.StringCacheSignNodeDao;
import cn.com.hyun.common.auth.TableCacheSignNodeDao;
import cn.com.hyun.framework.cache.config.CacheConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.StringTokenizer;

@Configuration
public class MiddlewareConfiguration {

    /**
     * redis config
     */
    @Value("${cache.nodes}")
    private String cachedNodes;
    @Value("${cache.password}")
    private String cachePassword;

    @Bean
    public CacheConfig cacheConfig() {
        return CacheConfig.newInstance().setPassword(cachePassword);
    }

    @Bean
    public StringCacheSignNodeDao stringCacheSignNodeDao() {
        return new StringCacheSignNodeDao(buildCacheNodes(),cachePassword);
    }

    @Bean
    public TableCacheSignNodeDao tableCacheSignNodeDao() {
        return new TableCacheSignNodeDao(buildCacheNodes(),cachePassword);
    }
    @Bean
    public Jedis jedis(){
        return  stringCacheSignNodeDao().getJedis();
    }

    private HostAndPort buildCacheNodes() {
        StringTokenizer tokenizer = new StringTokenizer(cachedNodes, ":");
        String host = tokenizer.nextElement().toString();
        int port = Integer.valueOf(tokenizer.nextElement().toString());
        return  new HostAndPort(host,port);
    }

}
