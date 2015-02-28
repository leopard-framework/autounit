package io.leopard.autounit.rule;

import io.leopard.autounit.AutoUnitLog;

public class MethodRuleDeleteImpl extends AbstractMethodRule {
	MethodTemplate methodTemplate = new MethodTemplateImpl();

	@Override
	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) {
		if (!unitMethod.getName().equals("delete")) {
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

		Object result = unitMethod.invoke(unitMethod.getArgs());

		if (ruleStateChain.isLog()) {
			AutoUnitLog.log(unitMethod.getMethod(), result, unitMethod.getArgs());
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
