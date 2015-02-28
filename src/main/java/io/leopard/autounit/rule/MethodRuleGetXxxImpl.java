package io.leopard.autounit.rule;

public class MethodRuleGetXxxImpl extends AbstractMethodRule {
	private MethodTemplate methodTemplate = new MethodTemplateImpl();

	protected boolean isGetXxxMethod(UnitMethod unitMethod) {
		if (unitMethod.getName().matches("^get.+$")) {
			return true;
		}
		return false;
	}

	@Override
	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) {
		if (!this.isGetXxxMethod(unitMethod)) {
			return null;
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " start###########");
		}
		this.methodTemplate.add(unitMethod, ruleStateChain.isLog());

		Object result = unitMethod.invoke();
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
