package io.leopard.autounit.tson;

public class FieldValueBooleanImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return true;
		}
		return null;
	}

}
