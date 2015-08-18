package com.anson.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anson.user.model.Users;

@CacheNamespace
public interface UsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    int insertSelective(Users record);
    
    
    
    @Select(value = { " select * from users where id = #{id}" }) 
    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);
    
    @Update(value = {" update users set username = #{username},password= #{password},"
    		+ "phonenumber= #{phonenumber},createdat= #{createdat} where id = #{id} " })
    int updateByPrimaryKey(Users user);
}