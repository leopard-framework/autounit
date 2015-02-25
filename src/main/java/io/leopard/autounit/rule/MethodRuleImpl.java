package io.leopard.autounit.rule;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public RuleState invoke(Object bean, Method method, String[] names, Object[] args, Map<String, String> tson, RuleStateChain ruleStateChain) throws Exception {
		for (MethodRule rule : list) {
			RuleState state = rule.invoke(bean, method, names, args, tson, ruleStateChain);
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
		System.err.println("方法[" + method.toGenericString() + "]找不到自动单元测试规则.");

		Object result = method.invoke(bean, args);
		return new RuleState(this, result, false, true);
		// throw new UnsupportedOperationException("方法[" + method.toGenericString() + "]找不到自动单元测试规则.");
	}
}
