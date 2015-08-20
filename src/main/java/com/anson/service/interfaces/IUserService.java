package com.anson.service.interfaces;

import com.anson.user.model.Users;

public interface IUserService {
	public Users getUserById(long id);
	
	public String signUp(Users user);
	
	public long updateUserById(Users user);
}
