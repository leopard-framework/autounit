package io.leopard.autounit.rule;

import java.lang.reflect.Method;
import java.util.Map;

public class MethodRuleGetImpl extends AbstractMethodRule {

	@Override
	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, Map<String, String> tson, RuleStateChain ruleStateChain) throws Exception {
		if (!method.getName().equals("get")) {
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
		Object arg = toObject(method.getReturnType(), tson, names, args);

		addMethod.invoke(bean, arg);

		Object result = method.invoke(bean, args);
		return new RuleState(this, result);
	}

}
