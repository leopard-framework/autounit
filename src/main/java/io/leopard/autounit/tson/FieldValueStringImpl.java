package io.leopard.autounit.tson;

public class FieldValueStringImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(String.class)) {
			return "s";
		}
		return null;
	}

}
