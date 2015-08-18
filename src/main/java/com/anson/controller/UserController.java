package com.anson.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.anson.model.Users;
import com.anson.service.interfaces.IUserService;
import com.anson.util.MD5Parse;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/signUp")
	@ResponseBody
	public Users signUp(HttpServletRequest request){
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		if(phoneNumber.matches("d{13}")){
			//TODO
		}
		Users user = new Users(0L,userName,MD5Parse.parseStr2md5(password), phoneNumber,new Date());
		return user;
	}
	
	@RequestMapping(value="/{id}/user",method=RequestMethod.GET)
	public String getUserInfo(@PathVariable long id){
		System.out.println("id");
		Users user = userService.getUserById(id);
		System.out.println(JSON.toJSONString(user));
		Users user1 = userService.getUserById(id);
		System.out.println(JSON.toJSONString(user1));
		return "index";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String updateUserInfo(@PathVariable long id){
		System.out.println("id");
		
		Users user = new Users(1L,"hahaha","12345678901","asdf",new Date());
		int user1 = userService.updateUserById(user);
		System.out.println(JSON.toJSONString(user1));
		return "index";
	}
	
}
