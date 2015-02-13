package io.leopard.autounit;

import io.leopard.autounit.inject.Inject;
import io.leopard.autounit.inject.InjectImpl;

import java.lang.reflect.Field;

import javassist.util.proxy.MethodHandler;

/**
 * 自动单元测试.
 * 
 * @author 阿海
 *
 */
public class AutoUnit {

	public static <T> T mock(Class<T> clazz) {
		Inject inject = new InjectImpl();
		T bean;
		try {
			bean = clazz.newInstance();
		}
		catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			inject.inject(bean, field);
		}
		return bean;
	}

	public static <T> T dao(T bean) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) bean.getClass();
		MethodHandler methodHandler = new MethodHandlerImpl(bean);
		T proxy = ClassProxy.newProxyInstance(clazz, methodHandler);
		return proxy;
	}
}
