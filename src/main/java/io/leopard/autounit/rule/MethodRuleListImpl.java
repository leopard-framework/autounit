package io.leopard.autounit.rule;

import java.util.List;

public class MethodRuleListImpl extends AbstractMethodRule {
	private MethodTemplate methodTemplate = new MethodTemplateImpl();

	@Override
	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) {
		if (!unitMethod.getName().equals("list")) {
			return null;
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " start###########");
		}
		try {
			methodTemplate.add(unitMethod, ruleStateChain.isLog());
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Object result = unitMethod.invoke();

		List<?> list = (List<?>) result;
		if (list == null || list.size() <= 0) {
			throw new RuntimeException("list方法没有返回记录.");
		}

		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
