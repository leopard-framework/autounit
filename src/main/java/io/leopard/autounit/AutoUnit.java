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

	public static <T> T dao(T bean) {
		MethodHandler methodHandler = new MethodHandlerImpl(bean);
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) bean.getClass();
		Inject inject = new InjectImpl();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			inject.inject(bean, field);
		}
		T proxy = ClassProxy.newProxyInstance(clazz, methodHandler);
		return proxy;
	}

}
