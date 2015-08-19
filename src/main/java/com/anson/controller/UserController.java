package com.anson.controller;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.anson.service.interfaces.IUserService;
import com.anson.user.exception.IllegalParameterException;
import com.anson.user.model.BusinessExceptionModel;
import com.anson.user.model.Users;
import com.anson.util.MD5Parse;

@Controller
@RequestMapping("/users")
public class UserController {
	protected final Logger logger = Logger.getLogger(getClass());
	public final static int controllerCode = 1; //controllerCode识别每一个controller的唯一标识符，异常时使用
	
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping(value="/signUp",method=RequestMethod.POST)	
	@ResponseBody
	public String signUp(@RequestBody Map<String,Object> config) throws IllegalParameterException{
//		String phoneNumber = request.getParameter("phoneNumber");
//		String password = request.getParameter("password");
//		String userName = request.getParameter("userName");
//		System.out.println(phoneNumber);
		String password = (String) config.get("password");
		String userName = (String) config.get("userName");
		String phoneNumber = (String) config.get("phoneNumber");
		System.out.println(password);
		System.out.println(userName);
		if(phoneNumber==null||!phoneNumber.matches("\\d{11}")){
			throw new IllegalParameterException("输入电话号码格式错误！");
		}
		Users user = new Users(null,userName, phoneNumber,MD5Parse.parseStr2md5(password),null);
		String token = userService.signUp(user);
		
		return token;
	}
	
	@RequestMapping(value="/{id}/user",method=RequestMethod.GET)
	@ResponseBody
	public Users getUserInfo(@PathVariable long id){
		System.out.println("id");
		Users user = userService.getUserById(id);
		System.out.println(JSON.toJSONString(user));
		Users user1 = userService.getUserById(id);
		System.out.println(JSON.toJSONString(user1));
		return user;
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String updateUserInfo(@PathVariable long id){
		System.out.println("id");
		Users user = new Users(1L,"hahaha","12345678901","asdf",new Date());
		int user1 = userService.updateUserById(user);
		System.out.println(JSON.toJSONString(user1));
		return "index";
	}
	
	@ExceptionHandler
	@ResponseBody
	public BusinessExceptionModel exceptionHandler(Exception e,HttpServletRequest request, HttpServletResponse response ){
		logger.error(e);
		BusinessExceptionModel be =  new BusinessExceptionModel (101,e.getMessage());
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return be;
	}
}
