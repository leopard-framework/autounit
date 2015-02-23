package io.leopard.autounit.tson;

import java.util.Map;

public class Tson {

	private static TsonService tsonService = new TsonServiceImpl();

	public static <T> T toObject(Class<T> clazz, Map<String, Object> data) {
		try {
			return tsonService.toObject(clazz, data);
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
