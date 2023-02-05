package cn.com.hyun.framework.cache.client;

import cn.com.hyun.framework.cache.config.CacheConfig;
import cn.com.hyun.framework.cache.config.CacheNodes;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hyunwoo
 */
public class TableCacheDao extends CacheDao {

    public TableCacheDao() {
        this(new CacheNodes(), new CacheConfig());
    }

    public TableCacheDao(CacheNodes cacheNodes) {
        this(cacheNodes, new CacheConfig());
    }

    public TableCacheDao(CacheNodes cacheNodes, CacheConfig cacheConfig) {
        jedisCluster = new JedisCluster(cacheNodes.getNodes(), cacheConfig.getTimeout(), cacheConfig.getTimeout(),
                cacheConfig.getMaxRedirections(), cacheConfig.getPassword(), initConfig(cacheConfig));
    }

    public long add(String tableName, String key, String value) {
        return jedisCluster.hset(tableName, key, value);
    }

    public String addAll(String tableName, Map<String, String> entry) {
        return jedisCluster.hmset(tableName, entry);
    }

    public long add(String tableName, String key, String value, int expire) {
        long total = jedisCluster.hset(tableName, key, value);
        jedisCluster.expire(key, expire);

        return total;
    }

    public String addAll(String tableName, Map<String, String> entry, int expire) {
        String result = jedisCluster.hmset(tableName, entry);
        jedisCluster.expire(tableName, expire);

        return result;
    }

    public long delete(String tableName, String... keys) {
        return jedisCluster.hdel(tableName, keys);
    }

    public boolean exists(String tableName, String key) {
        return jedisCluster.hexists(tableName, key);
    }

    public long size(String tableName) {
        return jedisCluster.hlen(tableName);
    }

    public List<String> get(String tableName, String... keys) {
        return jedisCluster.hmget(tableName, keys);
    }

    public String get(String tableName, String key) {
        return jedisCluster.hget(tableName, key);
    }

    public Map<String, String> getAll(String tableName) {
        return jedisCluster.hgetAll(tableName);
    }

    public Set<String> keys(String tableName) {
        return jedisCluster.hkeys(tableName);
    }

    public List<String> values(String tableName) {
        return jedisCluster.hvals(tableName);
    }

    public long length(String tableName) {
        return jedisCluster.hlen(tableName);
    }
}
