package io.leopard.autounit;

import io.leopard.autounit.rule.MethodRule;
import io.leopard.autounit.rule.MethodRuleImpl;
import io.leopard.autounit.rule.RuleState;
import io.leopard.autounit.rule.RuleStateChain;

import java.lang.reflect.Method;
import java.util.Map;

import javassist.util.proxy.MethodHandler;

public class MethodHandlerImpl implements MethodHandler {

	private static MethodRule methodRule = new MethodRuleImpl();
	private final Object bean;

	private Map<String, String> tson;

	public MethodHandlerImpl(final Object bean, Map<String, String> tson) {
		this.bean = bean;
		this.tson = tson;
	}

	@Override
	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		String[] names = CtClassUtil.getParameterNames(thisMethod);
		RuleState state = methodRule.invoke(bean, thisMethod, names, args, tson, new RuleStateChain());
		return state.getResult();
	}

}