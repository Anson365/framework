package com.anson.user.exception;

public class BusinessExceptionModel {
	private int code;
	private String message;
	public BusinessExceptionModel() {
		// TODO Auto-generated constructor stub
	}
	
	public BusinessExceptionModel(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
