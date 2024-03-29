package io.leopard.autounit.inject;

import java.lang.reflect.Field;

/**
 * 对象注入.
 * 
 * @author 阿海
 *
 */
public interface Inject {

	Inject inject(Object bean, Field field);

	/**
	 * 清空数据.
	 * 
	 * @return
	 */
	boolean clean();
}
