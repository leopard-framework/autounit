package io.leopard.autounit.rule;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodRuleImpl implements MethodRule {

	private List<MethodRule> list = new ArrayList<MethodRule>();

	public MethodRuleImpl() {
		list.add(new MethodRuleIGetImpl());
		list.add(new MethodRuleGetImpl());
	}

	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, RuleStateChain ruleStateChain) throws Exception {
		for (MethodRule rule : list) {
			RuleState state = rule.invoke(bean, method, names, args, ruleStateChain);
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
		throw new UnsupportedOperationException("方法[" + method.toGenericString() + "]找不到自动单元测试规则.");
	}
}
