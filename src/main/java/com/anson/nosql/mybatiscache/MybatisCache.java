package com.anson.nosql.mybatiscache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anson.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MybatisCache implements Cache {
	private static final Logger logger = Logger.getLogger(MybatisCache.class);

	@Autowired
	private JedisPool jedisPool;
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public MybatisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug("MybatisCache:id=" + id);
		this.id = id;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void putObject(Object key, Object value) {
		Jedis jedis = jedisPool.getResource();
		logger.debug("putObject:" + key + "=" + value);
		jedis.set(SerializeUtil.serialize(String.valueOf(key)), SerializeUtil.serialize(value));
	}

	public Object getObject(Object key) {
		Jedis jedis = jedisPool.getResource();
		byte[] keyB = SerializeUtil.serialize(String.valueOf(key));
		byte[] bytes  = jedis.get(keyB);
		Object result = SerializeUtil.unserialize(bytes);
		
		logger.debug("getObject:" + key + "=" + result);
		
		return result;
	}

	public Object removeObject(Object key) {
		Jedis jedis = jedisPool.getResource();
		byte[] keyB = SerializeUtil.serialize(String.valueOf(key));
		byte[] bytes  = jedis.get(keyB);
		Object result = SerializeUtil.unserialize(bytes);
		jedis.del(keyB);
		logger.debug("removeObject:" + key + "=" + result);
		return result;
	}

	public void clear() {
		Jedis jedis = jedisPool.getResource();
		jedis.flushDB();
	}

	public int getSize() {
		Jedis jedis = jedisPool.getResource();
		int size = Integer.parseInt(jedis.dbSize().toString());
		logger.debug("getSize:"+size);
		return size;
	}

	public ReadWriteLock getReadWriteLock() {
		
		return this.readWriteLock;
	}
}
