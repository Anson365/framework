package com.anson.test.util;

import org.junit.Test;

import com.anson.util.MD5Parse;

public class TestMD5Parse {
	@Test
	public void testParseStr2md5(){
		String password = "hello";
		String result = MD5Parse.parseStr2md5(password);
		System.out.println(result);
	}
	
	@Test
	public void test(){
		System.out.println(String.valueOf("hello world"));
	}
}
