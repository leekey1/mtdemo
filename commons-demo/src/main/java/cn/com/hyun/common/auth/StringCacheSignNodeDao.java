package cn.com.hyun.common.auth;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;


public class StringCacheSignNodeDao extends  CacheSignNodeDao {


    public StringCacheSignNodeDao(HostAndPort hostAndPort, String password) {
        super(hostAndPort,password);
    }


    public long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);//.longValue();
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

}
