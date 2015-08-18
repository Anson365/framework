package com.anson.test.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.anson.util.PinYinUtil;

public class TestPinYinUtil {
	@Test
	public void testGetPinYin(){
		String test = "ÄãºÃ12sdfAAVÅ®";
		String temp1 = PinYinUtil.getPinYin(test, PinYinUtil.FIRST_CHAR);
		String temp2 = PinYinUtil.getPinYin(test, PinYinUtil.QUANPIN);
		System.out.println(temp1);
		System.out.println(temp2);
		assertEquals("NH12SDFAAVN", temp1);
		assertEquals("NIHAO12SDFAAVNV",temp2);
	}
}
