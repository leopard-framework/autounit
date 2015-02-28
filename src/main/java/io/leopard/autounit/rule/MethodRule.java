package io.leopard.autounit.rule;


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
	 * @param unitMethod
	 *            被测试的方法信息
	 * @return
	 * @throws Exception
	 */
	RuleState invoke(UnitMethod unitMethod, RuleStateChain ruleStateChain) ;
}
