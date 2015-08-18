package com.anson.user.exception;

/**
 * 参数非法异常
 *
 */
public class IllegalParameterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7109111998740947292L;

	public IllegalParameterException(String msg) {
		super(msg);
	}
}
