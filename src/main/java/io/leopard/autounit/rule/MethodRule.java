package io.leopard.autounit.rule;

import java.lang.reflect.Method;

/**
 * 方法验证规则.
 * 
 * @author 阿海
 *
 */
public interface MethodRule {

	/**
	 * 方法验证.
	 * 
	 * @param bean
	 *            被测试的bean
	 * @param method
	 *            被测试方法
	 * @param names
	 *            方法名称数组
	 * @param args
	 *            方法参数值数组
	 * @param ruleStateChain
	 *            规则调用链
	 * @return
	 * @throws Exception
	 */
	RuleState invoke(Object bean, Method method, String[] names, Object[] args, RuleStateChain ruleStateChain) throws Exception;
}
