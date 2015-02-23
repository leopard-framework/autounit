package io.leopard.autounit.tson;

public class FieldValueLongImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(long.class) || clazz.equals(Long.class)) {
			return 1L;
		}
		return null;
	}

}
