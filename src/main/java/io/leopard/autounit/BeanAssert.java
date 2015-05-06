package io.leopard.autounit;

import io.leopard.autounit.tson.FieldValue;
import io.leopard.autounit.tson.FieldValueImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanAssert {

	public static void assertBean(Object bean) {
		assertBean(bean, false);
	}

	public static void assertBean(Object bean, boolean log) {
		Object mock = new BeanStubber("").dao(bean, log);
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.isBridge()) {
				continue;
			}
			Object[] args = getArgs(method);

//			System.err.println("assertBean:" + method.toGenericString() + " " + args.length);
			// for (Object arg : args) {
			// System.err.println("arg:" + arg + " " + arg.getClass().getName());
			// }
			try {
				method.invoke(mock, args);
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				System.err.println("方法[" + method.toGenericString() + "]调用出错.");
				// throw (RuntimeException) e.getCause();
				// e.printStackTrace();
			}
		}
	}

	private static FieldValue fieldValue = new FieldValueImpl();

	protected static Object[] getArgs(Method method) {
		Class<?>[] types = method.getParameterTypes();
		String[] names = CtClassUtil.getParameterNames(method);
		
		Object[] args = new Object[method.getParameterCount()];
		for (int i = 0; i < args.length; i++) {
			try {
				args[i] = fieldValue.get(types[i], names[i]);
			}
			catch (Exception e) {
				throw new RuntimeException("初始化参数[" + names[i] + "]失败", e);
			}
		}
		return args;
	}
}
