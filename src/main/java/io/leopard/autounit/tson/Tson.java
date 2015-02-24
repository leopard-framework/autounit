package io.leopard.autounit.tson;

import java.lang.reflect.Field;
import java.util.Map;

public class Tson {

	private static TsonService tsonService = new TsonServiceImpl();

	public static Object toObject(Class<?> type, Map<String, String> tson, String[] names, Object[] args) {
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
			try {
				field.set(arg, args[i]);
			}
			catch (IllegalArgumentException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			catch (IllegalAccessException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		return arg;
	}

	public static <T> T toObject(Class<T> clazz, Map<String, String> data) {
		try {
			return tsonService.toObject(clazz, data);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static Map<String, String> toMap(String tson) {
		try {
			return tsonService.toMap(tson);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static <T> T toObject(Class<T> clazz, String tson) {
		try {
			return tsonService.toObject(clazz, tson);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
