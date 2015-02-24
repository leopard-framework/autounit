package io.leopard.autounit.rule;

import io.leopard.autounit.tson.Tson;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractMethodRule implements MethodRule {

	protected boolean isAssignableFrom(Object bean, String className) {
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		return this.isAssignableFrom(bean, clazz);
	}

	protected boolean isAssignableFrom(Object bean, Class<?> clazz) {
		return bean.getClass().isAssignableFrom(clazz);
	}

	protected Object toObject(Class<?> type, Map<String, String> tson, String[] names, Object[] args) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Object arg = Tson.toObject(type, tson);

		for (int i = 0; i < names.length; i++) {
			Field field;
			try {
				field = type.getDeclaredField(names[i]);
			}
			catch (NoSuchFieldException e) {
				continue;
			}
			field.setAccessible(true);
			field.set(arg, args[i]);
		}

		return arg;
	}
}
