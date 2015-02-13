package io.leopard.autounit.rule;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodRuleImpl implements MethodRule {

	private List<MethodRule> list = new ArrayList<MethodRule>();

	public MethodRuleImpl() {
		list.add(new MethodRuleIGetImpl());
	}

	public Object invoke(Object bean, Method method, String[] names, Object[] args) throws Exception {
		for (MethodRule rule : list) {
			Object value = rule.invoke(bean, method, names, args);
			if (rule.isChecked()) {
				return value;
			}
		}
		throw new UnsupportedOperationException("方法[" + method.toGenericString() + "]找不到自动单元测试规则.");
	}

	@Override
	public void start() {
		for (MethodRule rule : list) {
			rule.start();
		}
	}

	@Override
	public boolean isChecked() {
		return true;
	}

}
