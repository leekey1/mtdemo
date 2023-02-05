package cn.com.hyun.framework.cache.client;

import cn.com.hyun.framework.cache.config.CacheConfig;
import cn.com.hyun.framework.cache.config.CacheNodes;
import redis.clients.jedis.JedisCluster;

/**
 * Created by hyunwoo
 */
public class StringCacheDao extends CacheDao {

    public StringCacheDao() {
        this(new CacheNodes(), new CacheConfig());
    }

    public StringCacheDao(CacheNodes cacheNodes) {
        this(cacheNodes, new CacheConfig());
    }

    public StringCacheDao(CacheNodes cacheNodes, CacheConfig cacheConfig) {
        jedisCluster = new JedisCluster(cacheNodes.getNodes(), cacheConfig.getTimeout(), cacheConfig.getTimeout(),
                cacheConfig.getMaxRedirections(), cacheConfig.getPassword(), initConfig(cacheConfig));
    }

    public String add(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public String add(String key, String value, int expire) {
        return jedisCluster.setex(key, expire, value);
    }

    public boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }
}
