package com.anson.util;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtil {
	public static final int FIRST_CHAR = 0; //拼音首字母
	public static final int QUANPIN = 1; //拼音
	
	/**
	 * @param String 汉字
	 * @return String 拼音
	 */
	public static String getPinYin(String chn,int type){
		HanyuPinyinOutputFormat py = new HanyuPinyinOutputFormat();
		py.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 					//无音调
		py.setCaseType(HanyuPinyinCaseType.UPPERCASE);						//字母大写
		py.setVCharType(HanyuPinyinVCharType.WITH_V);						//带v
		StringBuilder str = new StringBuilder();
		char[] chnArray = chn.trim().toCharArray();
		try {
			switch(type){
				case 0	:{
					for(char c:chnArray){
						if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){ 		//如果是汉字	
							String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, py);
							str.append(temp[0].toCharArray()[0]);     								//取第一个排除多音字情况
						}else{
							str.append(Character.toString(c).toUpperCase());
						}
					}
				}break;
				default	:{
					for(char c:chnArray){
						if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){ 		//如果是汉字	
							String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, py);
							str.append(temp[0]);     								//取第一个排除多音字情况
						}else{
							str.append(Character.toString(c).toUpperCase());
						}
					}
				}
			}
			
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}
}
