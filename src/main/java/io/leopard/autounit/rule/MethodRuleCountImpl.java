package io.leopard.autounit.rule;

public class MethodRuleCountImpl extends AbstractMethodRule {
	private MethodTemplate methodTemplate = new MethodTemplateImpl();

	@Override
	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) {
		if (!unitMethod.getName().equals("count")) {
			return null;
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " start###########");
		}

		methodTemplate.add(unitMethod, ruleStateChain.isLog());

		Object result = unitMethod.invoke();

		int count = (Integer) result;
		if (count <= 0) {
			throw new RuntimeException("count方法没有返回记录条数.");
		}
		if (ruleStateChain.isLog()) {
			System.err.println("############" + unitMethod.toGenericString() + " end###########");
		}
		return new RuleState(this, result);
	}

}
