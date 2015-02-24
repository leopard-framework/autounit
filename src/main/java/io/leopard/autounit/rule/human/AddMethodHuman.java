package io.leopard.autounit.rule.human;

import io.leopard.autounit.AutoUnitLog;
import io.leopard.autounit.tson.Tson;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Assert;

public class AddMethodHuman implements MethodHuman {

	private Object bean;
	private boolean log;

	public AddMethodHuman(Object bean, boolean log) {
		this.bean = bean;
		this.log = log;
	}

	@Override
	public Method find() throws Exception {
		Class<?> clazz = bean.getClass();
		for (Method method : clazz.getDeclaredMethods()) {
			// System.out.println(method.isBridge() + "  :  " + method.toGenericString());
			if (method.getName().equals("add") && !method.isBridge()) {
				return method;
			}
		}
		return null;
	}

	public Object invokeAndAssert(Map<String, String> tson, String[] names, Object[] args) throws Exception {
		Method addMethod = this.find();
		if (addMethod == null) {
			return null;
		}
		System.err.println("addMethod:" + addMethod.toGenericString());
		// System.err.println("type:" + addMethod.getParameterTypes()[0]);
		Object arg = Tson.toObject(addMethod.getParameterTypes()[0], tson, names, args);

		Object result = addMethod.invoke(bean, arg);

		if (log) {
			// System.err.println("call:" + addMethod.toGenericString());
			// System.err.println("result:" + result);
			AutoUnitLog.log(addMethod, args, result);
		}
		boolean success = (Boolean) result;
		Assert.assertTrue("调用add方法没有返回true.", success);
		return success;
	}

}
