package io.leopard.autounit;

import java.lang.reflect.Method;

public class AutoUnitLog {

	public static void log(Method method, Object[] args, Object result) {
		System.err.println("call:" + method.toGenericString());
		System.err.println("args:" + args);
		System.err.println("result:" + result);
		System.err.println("########################################");
	}
}
