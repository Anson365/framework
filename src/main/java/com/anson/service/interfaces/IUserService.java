package com.anson.service.interfaces;

import com.anson.user.model.Users;

public interface IUserService {
	public Users getUserById(long id);
	
	public int updateUserById(Users user);
}
