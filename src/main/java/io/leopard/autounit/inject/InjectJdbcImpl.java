package io.leopard.autounit.inject;

import java.lang.reflect.Field;

public class InjectJdbcImpl implements Inject {

	@Override
	public Inject inject(Object bean, Field field) {
		// System.out.println("InjectJdbcImpl inject:" + field.getName());
		return null;
	}

	@Override
	public boolean clean() {
		// TODO Auto-generated method stub
		return false;
	}

}
