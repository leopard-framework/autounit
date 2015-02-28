package io.leopard.autounit.rule;

public class MethodRuleAddImpl extends AbstractMethodRule {

	private MethodTemplate methodTemplate = new MethodTemplateImpl();

	@Override
	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) {
		if (!unitMethod.getName().equals("add")) {
			return null;
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " start###########");
		}
		Object result;
		try {
			result = methodTemplate.add(unitMethod, ruleStateChain.isLog());
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
