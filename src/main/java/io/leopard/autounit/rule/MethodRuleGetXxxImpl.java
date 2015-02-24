package io.leopard.autounit.rule;

import io.leopard.autounit.rule.human.AddMethodHuman;

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

		new AddMethodHuman(bean, ruleStateChain.isLog()).invokeAndAssert(tson, names, args);

		Object result = method.invoke(bean, args);
		return new RuleState(this, result);
	}

}
