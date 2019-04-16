package com.test.activiti.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Vo2MapUtils {
	
	/**
	 * 
	 * @param vo
	 * @param check  是否放入null值  true不放 ，false放
	 * @return
	 */
	public static Map<String, Object> vo2Map(Object vo,boolean check){
		Field[] fs = vo.getClass().getDeclaredFields();
		Map<String, Object>map=new HashMap<>();
		 for (int i = 0; i < fs.length; i++) {
	            Field f = fs[i];
	            f.setAccessible(true); // 设置些属性是可以访问的
	            Object val = new Object();
	            try {
	                val = f.get(vo);// 得到此属性的值
	                //判断是否放入null值
	                if (check) {
						if (val!=null) {
							map.put(f.getName(), val);// 设置键值
						}
					}else {
						map.put(f.getName(), val);// 设置键值
					}
	                
	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
		 }
		
		return map;
	}

}
