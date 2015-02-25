package io.leopard.autounit;

import java.lang.reflect.Method;
import java.util.Map;

import io.leopard.autounit.rule.MethodRule;
import io.leopard.autounit.rule.MethodRuleImpl;
import io.leopard.autounit.rule.RuleState;
import io.leopard.autounit.rule.RuleStateChain;
import io.leopard.autounit.tson.Tson;
import javassist.util.proxy.MethodHandler;

public class BeanStubber {

	private static MethodRule methodRule = new MethodRuleImpl();

	private String tson;

	public BeanStubber(String tson) {
		this.tson = tson;
	}

	public <T> T dao(T bean) {
		return this.dao(bean, false);
	}

	public <T> T dao(T bean, boolean log) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) bean.getClass();
		T proxy = ClassProxy.newProxyInstance(clazz, new MethodHandlerImpl(bean, log, Tson.toMap(tson)));
		return proxy;
	}

	public class MethodHandlerImpl implements MethodHandler {

		private final Object bean;
		private boolean log;

		private Map<String, String> tson;

		public MethodHandlerImpl(final Object bean, boolean log, Map<String, String> tson) {
			this.bean = bean;
			this.log = log;
			this.tson = tson;
		}

		@Override
		public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
			AutoUnit.rollabck();
			String[] names = CtClassUtil.getParameterNames(thisMethod);
			RuleState state = methodRule.invoke(bean, thisMethod, names, args, tson, new RuleStateChain(log));
			AutoUnit.rollabck();
			return state.getResult();
		}

	}
}