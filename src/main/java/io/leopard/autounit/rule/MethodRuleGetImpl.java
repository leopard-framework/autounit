package io.leopard.autounit.rule;

import io.leopard.autounit.rule.human.AddMethodHuman;

import java.lang.reflect.Method;
import java.util.Map;

public class MethodRuleGetImpl extends AbstractMethodRule {

	@Override
	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, Map<String, String> tson, RuleStateChain ruleStateChain) throws Exception {
		if (!method.getName().equals("get")) {
			return null;
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + method.toGenericString() + " start###########");
		}
		new AddMethodHuman(bean, ruleStateChain.isLog()).invokeAndAssert(tson, names, args);

		Object result = method.invoke(bean, args);
		if (ruleStateChain.isLog()) {
			System.err.println("############" + method.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
