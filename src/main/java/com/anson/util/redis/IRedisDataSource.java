package com.anson.util.redis;

import redis.clients.jedis.ShardedJedis;

public interface IRedisDataSource {
	//获取客户端
	public ShardedJedis getResource();
	//将资源返还给pool
	public void close();
}
