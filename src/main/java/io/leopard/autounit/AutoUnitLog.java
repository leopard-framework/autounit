package io.leopard.autounit;

import java.lang.reflect.Method;
import java.util.Date;

public class AutoUnitLog {

	public static void log(Method method, Object result, Object... args) {
		System.err.println("call:" + method.toGenericString());
		System.err.println("args:" + argsToString(args));
		System.err.println("result:" + result);
		System.err.println("########################################");
	}

	protected static String argsToString(Object... args) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			if (arg instanceof Number) {
				sb.append(arg);
			}
			else if (arg instanceof String) {
				sb.append(arg);
			}
			else if (arg instanceof Date) {
				sb.append(((Date) arg).getTime());
			}
			else {
				sb.append(arg);
			}
			sb.append(" ");
		}
		return sb.toString();
	}
}
