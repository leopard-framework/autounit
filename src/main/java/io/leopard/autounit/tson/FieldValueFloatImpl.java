package io.leopard.autounit.tson;

public class FieldValueFloatImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(float.class) || clazz.equals(Float.class)) {
			return 1F;
		}
		return null;
	}

}
