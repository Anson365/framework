package com.anson.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anson.dao.UsersMapper;
import com.anson.service.interfaces.IUserService;
import com.anson.user.model.Users;

import redis.clients.jedis.Jedis;

@Service("userService")
@Transactional(readOnly=true)
public class UserService implements IUserService {
	@Autowired
	private UsersMapper userMapper;
	@Autowired
	private Jedis jedis;
	
	
	@SuppressWarnings("resource")
	@Cacheable
	public Users getUserById(long id) {
		Users user = userMapper.selectByPrimaryKey(id);
//		jedis = new Jedis("localhost",6379);
		String value = jedis.get(id+"");
		System.out.println(value);
		return user;
	}
	
	@Transactional(readOnly=false)
	public int updateUserById(Users user) {
		// TODO Auto-generated method stub
		String value = new Date().toString();
		jedis.set(user.getId()+"", value);
		System.out.println(value);
		jedis.expire(user.getId()+"", 30);
		return userMapper.updateByPrimaryKey(user);
	}
	
	@Transactional(readOnly=false)
	public String signUp(Users user) {
		int id = userMapper.insert(user);
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		jedis.set(token	, String.valueOf(id));
		jedis.expire(token, 60*60*24*7);
		return token;
	}
	
}
