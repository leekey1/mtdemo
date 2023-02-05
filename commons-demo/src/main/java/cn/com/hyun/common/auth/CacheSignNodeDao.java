package cn.com.hyun.common.auth;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Fero on 2017/6/9.
 */
@Repository
public class CacheSignNodeDao {
    protected JedisPool jedisPool;

    public CacheSignNodeDao(){

    }

    public CacheSignNodeDao(HostAndPort hostAndPort, String password){
      //  System.out.println("host "+hostAndPort.getHost() + " port " + hostAndPort.getPort() + " pwd: " +password);
        if(password!=null && password.trim().isEmpty()) this.jedisPool = new JedisPool(buildGenericObjectPoolConfig(), hostAndPort.getHost(), hostAndPort.getPort(), 2000);
       else this.jedisPool = new JedisPool(buildGenericObjectPoolConfig(), hostAndPort.getHost(), hostAndPort.getPort(), 2000, password);
    }
    private GenericObjectPoolConfig buildGenericObjectPoolConfig(){
        GenericObjectPoolConfig config=  new GenericObjectPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(50);
        config.setMinIdle(8);//设置最小空闲数
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
//Idle时进行连接扫描
        config.setTestWhileIdle(true);
//表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setTimeBetweenEvictionRunsMillis(30000);
//表示idle object evitor每次扫描的最多的对象数
        config.setNumTestsPerEvictionRun(10);
//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(60000);
        return config;
    }


    public String get(String key)  {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }


    public boolean exists(String tableName, String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hexists(tableName, key);//.booleanValue();
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public Jedis getJedis(){
        return jedisPool.getResource();

    }

    public void add(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }
    public String add(String key, String value, int expire)  {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key,expire, value);
        } finally {
            jedis.close();
        }
    }


    public void add(String tableName,String key, String value)  {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(tableName,key, value);
        } finally {
            jedis.close();
        }
    }

    public boolean exists(String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);//.booleanValue();
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

}
