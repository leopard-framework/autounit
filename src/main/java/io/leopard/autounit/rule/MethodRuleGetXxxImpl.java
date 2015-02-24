package io.leopard.autounit.rule;

import io.leopard.autounit.tson.Tson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class MethodRuleGetXxxImpl extends AbstractMethodRule {

	protected boolean isGetXxxMethod(Method method) {
		if (method.getName().matches("^get.+$")) {
			return true;
		}
		return false;
	}

	@Override
	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, Map<String, String> tson, RuleStateChain ruleStateChain) throws Exception {
		if (!this.isGetXxxMethod(method)) {
			return null;
		}

		Method addMethod;
		try {
			addMethod = bean.getClass().getDeclaredMethod("add", method.getReturnType());
		}
		catch (NoSuchMethodException e) {
			// 没有add方法
			return null;
		}

		// System.err.println("tson:" + tson);
		Object arg = Tson.toObject(method.getReturnType(), tson);

		// System.out.println("names:" + names[0] + " clazz:" + method.getReturnType());
		Field field = method.getReturnType().getDeclaredField(names[0]);
		// System.out.println("names:" + names[0] + " field:" + args[0]);
		field.setAccessible(true);
		field.set(arg, args[0]);

		Object addResult = addMethod.invoke(bean, arg);

		Object result = method.invoke(bean, args);
		return new RuleState(this, result);
	}

}
