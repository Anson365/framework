package com.anson.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anson.dao.UsersMapper;
import com.anson.model.Users;
import com.anson.service.interfaces.IUserService;

import redis.clients.jedis.Jedis;

@Service("userService")
@Transactional(readOnly=true)
public class UserService implements IUserService {
	
	private UsersMapper userMapper;
	@Autowired
	private Jedis jedis;
	
	@SuppressWarnings("resource")
	@Cacheable
	public Users getUserById(long id) {
		Users user = userMapper.selectByPrimaryKey(id);
		Jedis jedis = new Jedis("localhost",6379);
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
	
	public UsersMapper getUserMapper() {
		return userMapper;
	}
	
	@Autowired
	public void setUserMapper(UsersMapper userMapper) {
		this.userMapper = userMapper;
	}
	
}
