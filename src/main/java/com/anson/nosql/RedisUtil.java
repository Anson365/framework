package com.anson.nosql;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anson.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository
public class RedisUtil {
	
	@Autowired
	private JedisPool jedisPool;
	
	
	private  final int tokenExpire = 60* 60*24*15; //设置时间为15天
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
	 * 设置存取内容以及时间(利用序列化可存储所以的对象)
	 * @param key 
	 * @param value 
	 * @param expire int 有效时间，0默认为永久（seconds）
	 */
	public void set(Object key,Object value,int expire){
		Jedis jedis = jedisPool.getResource();
		byte[] keyB = SerializeUtil.serialize(key);
		jedis.set(keyB, SerializeUtil.serialize(value));
		if(expire>0){
			logger.debug(key+":"+expire+"="+value);
			jedis.expire(keyB, expire);
		}
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Object get(Object key){
		Jedis jedis = jedisPool.getResource();
		byte[] resultTemp = jedis.get(SerializeUtil.serialize(key));
		Object result = SerializeUtil.unserialize(resultTemp);  //反序列化操作
		logger.debug(key+":"+"="+result);
		return result ;
	}
	
	/**
	 * 取出redis中的值并设置新的时间，若expire<1,则默认删除
	 * @param key String
	 * @param expire int 设置新的缓存时间
	 * @return
	 */
	public Object getWithNewExpire(Object key,int expire){
		Jedis jedis = jedisPool.getResource();
		byte[] keyB = SerializeUtil.serialize(key);
		byte[] resultTemp = jedis.get(keyB);
		Object result = SerializeUtil.unserialize(resultTemp);
		if(result != null){
			if(expire >0){
				jedis.expire(keyB,expire);
			}else{
				jedis.del(keyB);
			}
		}
		logger.debug(key+":"+expire+"="+result);
		return result;
	}
	
	public void remove(Object key){
		Jedis jedis = jedisPool.getResource();
		logger.debug(key+"=>remove");
		byte[] keyB = SerializeUtil.serialize(key);
		jedis.del(keyB);
	}
}
