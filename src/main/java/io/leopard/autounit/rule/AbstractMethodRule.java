package io.leopard.autounit.rule;

public abstract class AbstractMethodRule implements MethodRule {

	protected boolean isAssignableFrom(Object bean, String className) {
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		return this.isAssignableFrom(bean, clazz);
	}

	protected boolean isAssignableFrom(Object bean, Class<?> clazz) {
		return bean.getClass().isAssignableFrom(clazz);
	}
}
