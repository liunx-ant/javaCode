package com.liunx.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
	public static Map<String, Object> objectToMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (obj == null) {
				return null;
			}
			Field[] declaredFields = obj.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (IllegalArgumentException e) {
			System.out.println("转MAP出错！");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("转MAP出错！！");
			e.printStackTrace();
		}
		return map;
	}
}
