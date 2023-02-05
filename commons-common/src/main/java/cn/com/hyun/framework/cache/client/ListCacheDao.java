package cn.com.hyun.framework.cache.client;

import cn.com.hyun.framework.cache.config.CacheConfig;
import cn.com.hyun.framework.cache.config.CacheNodes;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * Created by hyunwoo
 */
public class ListCacheDao extends CacheDao {

    public ListCacheDao() {
        this(new CacheNodes(), new CacheConfig());
    }

    public ListCacheDao(CacheNodes cacheNodes) {
        this(cacheNodes, new CacheConfig());
    }

    public ListCacheDao(CacheNodes cacheNodes, CacheConfig cacheConfig) {
        jedisCluster = new JedisCluster(cacheNodes.getNodes(), cacheConfig.getTimeout(), cacheConfig.getTimeout(),
                cacheConfig.getMaxRedirections(), cacheConfig.getPassword(), initConfig(cacheConfig));
    }

    public long add(String key, String... values) {
        return jedisCluster.rpush(key, values);
    }

    public long add(String key, int expire, String... values) {
        long total = jedisCluster.rpush(key, values);
        jedisCluster.expire(key, expire);

        return total;
    }

    public long delete(String key, String value) {
        return jedisCluster.lrem(key, 0, value);
    }

    public boolean exists(String key, String value) {
        return getAll(key).contains(value);
    }

    public long size(String key) {
        return jedisCluster.llen(key);
    }

    public String get(String key, int index) {
        return jedisCluster.lindex(key, index);
    }

    public List<String> getAll(String key) {
        return jedisCluster.lrange(key, 0, -1);
    }
}
