package com.anson.nosql;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository
public class RedisUtil {
	
	@Autowired
	private JedisPool jedisPool;
	
	
	private  final int tokenExpire = 60* 60*24*15;
	private static final Logger logger = Logger.getLogger(RedisUtil.class);
	
	
	/**
	 * 生成UUID作为键，id作为值放入redis
	 * @param id long
	 * @return UUID String
	 */
	public String genToken(long id){
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		logger.debug(token+":"+tokenExpire+"="+id);
		set(token,String.valueOf(id),tokenExpire);
		return token;
	}
	
	/**
	 * 设置存取内容以及时间
	 * @param key String
	 * @param value String
	 * @param expire int 有效时间，0默认为永久（seconds）
	 */
	public void set(String key,String value,int expire){
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		if(expire>0){
			logger.debug(key+":"+expire+"="+value);
			jedis.expire(key, expire);
		}
	}
	
	/**
	 * @param key
	 * @return
	 */
	public String get(String key){
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		logger.debug(key+":"+"="+result);
		return result ;
	}
	
	/**
	 * 取出redis中的值并设置新的时间，若expire<1,则默认删除
	 * @param key String
	 * @param expire int 设置新的缓存时间
	 * @return
	 */
	public String getWithNewExpire(String key,int expire){
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		if(result != null){
			if(expire >0){
				jedis.expire(key,expire);
			}else{
				jedis.del(key);
			}
		}
		logger.debug(key+":"+expire+"="+result);
		return result;
	}
	
	public void remove(String key){
		Jedis jedis = jedisPool.getResource();
		logger.debug(key+"=>remove");
		jedis.del(key);
	}
}
