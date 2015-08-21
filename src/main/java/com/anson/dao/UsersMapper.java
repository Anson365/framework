package com.anson.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;

import com.anson.user.model.Users;

@CacheNamespace/*(implementation=RedisCache.class)*/
public interface UsersMapper {
	
	@Results(value={
		@Result(column="id",property="id",javaType=long.class),
		@Result(column="username",property="username",javaType=java.lang.String.class),
		@Result(column="password",property="password",javaType=String.class),
		@Result(column="phonenumber",property="phonenumber",javaType=String.class),
		@Result(column="createdat",property="createdat",javaType=java.util.Date.class)
	})
	
	
	
    int deleteByPrimaryKey(Long id);
	
    @Select(value=" insert into users(username,password,phonenumber) values(#{username},#{password},#{phonenumber}) returning id ") 
    long insert(Users record);

    int insertSelective(Users record);
    
    
    
    @Select(value = { " select * from users where id = #{id}" }) 
    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);
    
    @Update(value = {" update users set username = #{username},password= #{password},"
    		+ "phonenumber= #{phonenumber},createdat= #{createdat} where id = #{id} " })
    int updateByPrimaryKey(Users user);
}