package io.leopard.autounit.tson;

public class FieldValueIntegerImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
			return 1;
		}
		return null;
	}

}
