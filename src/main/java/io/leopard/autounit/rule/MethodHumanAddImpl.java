package io.leopard.autounit.rule;

import io.leopard.autounit.AutoUnitLog;
import io.leopard.autounit.tson.Tson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodHumanAddImpl implements MethodHuman {

	@Override
	public Method find(Object bean) throws NoSuchMethodException {
		Class<?> clazz = bean.getClass();
		for (Method method : clazz.getDeclaredMethods()) {
			// System.out.println(method.isBridge() + "  :  " + method.toGenericString());
			if (method.getName().equals("add") && !method.isBridge()) {
				return method;
			}
		}
		throw new NoSuchMethodException("add");
	}

	@Override
	public Object invoke(UnitMethod unitMethod, boolean log) throws NoSuchMethodException {
		Method method = this.find(unitMethod.getBean());
		if (method == null) {
			return false;
		}
		// System.err.println("type:" + addMethod.getParameterTypes()[0]);
		Object arg = Tson.toObject(method.getParameterTypes()[0], unitMethod.getTson(), unitMethod.getNames(), unitMethod.getArgs());

		Object result;
		try {
			result = method.invoke(unitMethod.getBean(), arg);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		if (log) {
			// System.err.println("call:" + addMethod.toGenericString());
			// System.err.println("result:" + result);
			AutoUnitLog.log(method, result, arg);
		}
		return result;
	}

}
