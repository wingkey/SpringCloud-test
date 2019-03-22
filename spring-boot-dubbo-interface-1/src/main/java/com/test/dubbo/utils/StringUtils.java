package com.test.dubbo.utils;

public class StringUtils {

	public static boolean checkNull(String check) {
		if (check == null || "null".equals(check) || "".equals(check)) {
			return false;
		}
		return true;
	}
	
}
