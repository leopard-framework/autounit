package io.leopard.autounit.rule;

public class MethodRuleGetImpl extends AbstractMethodRule {
	private MethodTemplate methodTemplate = new MethodTemplateImpl();

	@Override
	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) {
		if (!unitMethod.getName().equals("get")) {
			return null;
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " start###########");
		}
		try {
			this.methodTemplate.add(unitMethod, ruleStateChain.isLog());
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Object result = unitMethod.invoke();
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
