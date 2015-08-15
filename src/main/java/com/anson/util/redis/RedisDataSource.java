package com.anson.util.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


@Repository(value="redisDataSource")
public class RedisDataSource implements IRedisDataSource {
	
	private static Logger log = Logger.getLogger(IRedisDataSource.class);
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public ShardedJedis getRedisClient() {
		try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
	}

	public void close() {
		shardedJedisPool.close();
	}

	public ShardedJedis getResource() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
