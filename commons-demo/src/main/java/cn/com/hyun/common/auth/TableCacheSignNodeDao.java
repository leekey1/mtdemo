package cn.com.hyun.common.auth;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by Fero on 2017/6/9.
 */
public class TableCacheSignNodeDao extends   CacheSignNodeDao {

    public TableCacheSignNodeDao(HostAndPort hostAndPort, String password) {
        super(hostAndPort,password);
    }


    public String get(String tableName, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(tableName, key);
        } finally {
            jedis.close();
        }
    }


    public String addAll(String tableName, Map<String, String> entry, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.hmset(tableName, entry);
            jedis.expire(tableName, expire);
            return result;
        } finally {
            jedis.close();
        }
    }

    public String addAll(String tableName, Map<String, String> entry) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hmset(tableName, entry);
        } finally {
            jedis.close();
        }
    }




}
