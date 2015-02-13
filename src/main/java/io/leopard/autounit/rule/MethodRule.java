package io.leopard.autounit.rule;

import java.lang.reflect.Method;

/**
 * 方法验证.
 * 
 * @author 阿海
 *
 */
public interface MethodRule {

	/**
	 * 方法验证.
	 * 
	 * @param bean
	 * @param method
	 * @param names
	 * @param args
	 * @return
	 * @throws Exception
	 */
	RuleState invoke(Object bean, Method method, String[] names, Object[] args, RuleStateChain ruleStateChain) throws Exception;
}
