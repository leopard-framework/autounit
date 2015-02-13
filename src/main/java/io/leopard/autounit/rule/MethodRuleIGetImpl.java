package io.leopard.autounit.rule;

import java.lang.reflect.Method;

public class MethodRuleIGetImpl extends AbstractMethodRule {

	@Override
	public Object invoke(Object bean, Method method, String[] names, Object[] args) throws Exception {
		if (!method.getName().equals("get")) {
			return null;
		}

		// if (!isAssignableFrom(bean, "IGet")) {
		// return null;
		// }

		this.checked = true;
		return method.invoke(bean, args);
	}

}
