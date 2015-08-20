package com.anson.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anson.dao.UsersMapper;
import com.anson.nosql.RedisUtil;
import com.anson.service.interfaces.IUserService;
import com.anson.user.model.Users;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("userService")
@Transactional(readOnly=true)
public class UserService implements IUserService {
	@Autowired
	private UsersMapper userMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private JedisPool jedisPool;
	
	
	@Cacheable
	public Users getUserById(long id) {
		
		Users user = userMapper.selectByPrimaryKey(id);
//		jedis = new Jedis("localhost",6379);
//		Jedis jedis = jedisPool.getResource();
//		String value = jedis.get(id+"");
//		System.out.println(value);
		return user;
	}
	
	@Transactional(readOnly=false)
	public long updateUserById(Users user) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String value = new Date().toString();
		jedis.set(user.getId()+"", value);
		System.out.println(value);
//		jedis.del(user.getId()+"");
		jedis.expire(user.getId()+"", 30);
		return userMapper.updateByPrimaryKey(user);
	}
	
	@Transactional(readOnly=false)
	public String signUp(Users user) {
		long id = userMapper.insert(user);
		String token = redisUtil.genToken(id);
		return token;
	}
	
}
