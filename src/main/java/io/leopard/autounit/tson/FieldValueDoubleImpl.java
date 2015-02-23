package io.leopard.autounit.tson;

public class FieldValueDoubleImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(double.class) || clazz.equals(Double.class)) {
			return 1D;
		}
		return null;
	}

}
