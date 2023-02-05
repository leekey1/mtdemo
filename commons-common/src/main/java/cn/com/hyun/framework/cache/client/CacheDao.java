package cn.com.hyun.framework.cache.client;

import cn.com.hyun.framework.cache.config.CacheConfig;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;

/**
 * Created by hyunwoo
 */
public abstract class CacheDao {
    protected JedisCluster jedisCluster;

    protected JedisPoolConfig initConfig(CacheConfig cacheConfig) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(cacheConfig.getMinIdle());
        jedisPoolConfig.setMaxIdle(cacheConfig.getMaxIdle());
        jedisPoolConfig.setMaxTotal(cacheConfig.getMaxSize());
        jedisPoolConfig.setMaxWaitMillis(cacheConfig.getTimeout());
        jedisPoolConfig.setFairness(cacheConfig.isFairness());

        return jedisPoolConfig;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public long delete(String key) {
        return jedisCluster.del(key);
    }

    public void close() throws IOException {
        if (null != jedisCluster) {
            jedisCluster.close();
        }
    }
}
