package io.leopard.autounit;

import java.lang.reflect.Field;

public class FieldFinder {

	@SuppressWarnings("unchecked")
	public static <T> T find(Object bean, Class<T> clazz) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().equals(clazz)) {
				field.setAccessible(true);
				try {
					return (T) field.get(bean);
				}
				catch (IllegalAccessException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		}
		throw new RuntimeException("找不到属性[" + clazz.getName() + "].");
	}
}
