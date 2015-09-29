package com.anson.util;

public class ObjectUtil {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param target
	 * @return
	 */
	public static boolean isStringEmpty(String target) {
//		if (target == null) {
//			return true;
//		} else if ("".equals(target.trim())) {
//			return true;
//		} else if ("null".equalsIgnoreCase(target.trim())) {
//			return true;
//		}
//		return false;
		if("null".equalsIgnoreCase(target.trim())){
			return true;
		}else{
			return isBlank(target);
		}
	}

	/**
	 * @param target
	 * @return
	 */
	public static boolean isObjectEmpty(Object target) {
		
		if (target == null) {
			return true;
		}
		if(target instanceof String){
			return isStringEmpty((String)target);
		}
		return false;
	}
	
	
	private static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
