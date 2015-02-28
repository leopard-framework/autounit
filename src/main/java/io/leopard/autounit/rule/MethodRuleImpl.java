package io.leopard.autounit.rule;

import java.util.ArrayList;
import java.util.List;

public class MethodRuleImpl implements MethodRule {

	private List<MethodRule> list = new ArrayList<MethodRule>();

	public MethodRuleImpl() {
		list.add(new MethodRuleAddImpl());
		list.add(new MethodRuleGetImpl());
		list.add(new MethodRuleGetXxxImpl());
		list.add(new MethodRuleDeleteImpl());
		list.add(new MethodRuleCountImpl());
		list.add(new MethodRuleListImpl());
	}

	public RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain)  {
		for (MethodRule rule : list) {
			RuleState state = rule.invoke(unitMethod, ruleStateChain);
			if (state == null) {
				continue;
			}
			ruleStateChain.add(state);
			if (state.isStop()) {
				return state;
			}
		}
		if (ruleStateChain.getLastVerifiedRuleState() != null) {
			return ruleStateChain.getLastVerifiedRuleState();
		}
		System.err.println("方法[" + unitMethod.toGenericString() + "]找不到自动单元测试规则.");

		Object result = unitMethod.invoke();
		return new RuleState(this, result, false, true);
		// throw new UnsupportedOperationException("方法[" + method.toGenericString() + "]找不到自动单元测试规则.");
	}
}
