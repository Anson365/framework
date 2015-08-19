package com.anson.nosql;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	@Autowired
	private static Jedis jedis;
	private static final int tokenExpire = 60* 60*24*15;
	
	
	/**
	 * 生成UUID作为键，id作为值放入redis
	 * @param id long
	 * @return UUID String
	 */
	public static String genToken(Long id){
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		set(token,String.valueOf(id),tokenExpire);
		return token;
	}
	
	/**
	 * 设置存取内容以及时间
	 * @param key String
	 * @param value String
	 * @param expire int 有效时间，0默认为永久（seconds）
	 */
	public static void set(String key,String value,int expire){
		jedis.set(key, value);
		if(expire>0){
			jedis.expire(key, expire);
		}
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static String get(String key){
		String result = jedis.get(key);
		return result ;
	}
	
	/**
	 * 取出redis中的值并设置新的时间，若expire<1,则默认删除
	 * @param key String
	 * @param expire int 设置新的缓存时间
	 * @return
	 */
	public static String getWithNewExpire(String key,int expire){
		String result = jedis.get(key);
		if(result == null){
			return result;
		}else{
			if(expire >0){
				jedis.expire(key,expire);
			}else{
				jedis.del(key);
			}
			return result ;
		}
	}
	
	public static void remove(String key){
		jedis.del(key);
	}
}
