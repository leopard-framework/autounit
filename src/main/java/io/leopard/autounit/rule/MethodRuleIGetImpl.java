package io.leopard.autounit.rule;

import java.lang.reflect.Method;
import java.util.Map;

public class MethodRuleIGetImpl extends AbstractMethodRule {

	@Override
	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, Map<String, String> tson, RuleStateChain ruleStateChain) throws Exception {
		if (!method.getName().equals("get")) {
			return null;
		}

		Object result = method.invoke(bean, args);

		return new RuleState(this, result);
	}

}
