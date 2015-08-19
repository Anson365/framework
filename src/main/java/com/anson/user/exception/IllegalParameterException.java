package com.anson.user.exception;

/**
 * 参数非法异常
 *
 */
public class IllegalParameterException extends Exception {
	/**
	 * 
	 */
	private final static long serialVersionUID = 7109111998740947292L;
	
	public final static int code = 101;

	public IllegalParameterException(String msg) {
		super(msg);
	}
}
