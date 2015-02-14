package io.leopard.autounit.inject;

import java.lang.reflect.Field;

public abstract class AbstractInject implements Inject {

	protected Object getFieldValue(Object bean, Field field) {
		// 判断是否已经有值

		field.setAccessible(true);
		Object value;
		try {
			value = field.get(bean);
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return value;
	}

	protected void setFieldValue(Object bean, Field field, Object value) {
		field.setAccessible(true);
		try {

			field.set(bean, value);
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
