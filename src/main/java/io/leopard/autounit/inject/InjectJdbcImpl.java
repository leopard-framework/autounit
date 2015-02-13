package io.leopard.autounit.inject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;

public class InjectJdbcImpl implements Inject {

	@Override
	public boolean inject(Object bean, Field field) {

		

		System.out.println("InjectJdbcImpl inject:" + field.getName());
		return false;
	}

}
