package cn.com.hyun.framework.cache.client;

import cn.com.hyun.framework.cache.config.CacheConfig;
import cn.com.hyun.framework.cache.config.CacheNodes;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * Created by hyunwoo
 */
public class SetCacheDao extends CacheDao {

    public SetCacheDao() {
        this(new CacheNodes(), new CacheConfig());
    }

    public SetCacheDao(CacheNodes cacheNodes) {
        this(cacheNodes, new CacheConfig());
    }

    public SetCacheDao(CacheNodes cacheNodes, CacheConfig cacheConfig) {
        jedisCluster = new JedisCluster(cacheNodes.getNodes(), cacheConfig.getTimeout(), cacheConfig.getTimeout(),
                cacheConfig.getMaxRedirections(), cacheConfig.getPassword(), initConfig(cacheConfig));
    }

    public long add(String key, String... values) {
        return jedisCluster.sadd(key, values);
    }

    public long add(String key, int expire, String... values) {
        long total = jedisCluster.sadd(key, values);
        jedisCluster.expire(key, expire);

        return total;
    }

    public long delete(String key, String... values) {
        return jedisCluster.srem(key, values);
    }

    public boolean exists(String key, String value) {
        return jedisCluster.sismember(key, value);
    }

    public long size(String key) {
        return jedisCluster.scard(key);
    }

    public Set<String> getAll(String key) {
        return jedisCluster.smembers(key);
    }
}
