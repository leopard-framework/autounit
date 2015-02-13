package io.leopard.autounit.rule;

import java.lang.reflect.Method;

public class MethodRuleGetImpl extends AbstractMethodRule {

	@Override
	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, RuleStateChain ruleStateChain) throws Exception {
		if (!method.getName().equals("get")) {
			return null;
		}

		Object result = method.invoke(bean, args);

		return new RuleState(this, result);
	}

}
