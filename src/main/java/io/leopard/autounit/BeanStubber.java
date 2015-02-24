package io.leopard.autounit;

import io.leopard.autounit.tson.Tson;
import javassist.util.proxy.MethodHandler;

public class BeanStubber {

	private String tson;

	public BeanStubber(String tson) {
		this.tson = tson;
	}

	public <T> T dao(T bean) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) bean.getClass();
		MethodHandler methodHandler = new MethodHandlerImpl(bean, Tson.toMap(tson));
		T proxy = ClassProxy.newProxyInstance(clazz, methodHandler);
		return proxy;
	}
}
