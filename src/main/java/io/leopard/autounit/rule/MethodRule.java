package io.leopard.autounit.rule;

import java.lang.reflect.Method;

/**
 * 方法验证.
 * 
 * @author 阿海
 *
 */
public interface MethodRule {

	void start();

	/**
	 * 是否已检查.
	 * 
	 * @return
	 */
	boolean isChecked();

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
	Object invoke(Object bean, Method method, String[] names, Object[] args) throws Exception;
}
