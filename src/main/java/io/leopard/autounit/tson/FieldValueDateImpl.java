package io.leopard.autounit.tson;

import java.util.Date;

public class FieldValueDateImpl implements FieldValue {

	@Override
	public Object get(Class<?> clazz, String name) {
		if (clazz.equals(Date.class)) {
			return new Date();
		}
		return null;
	}

}
