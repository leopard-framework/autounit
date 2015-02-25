package io.leopard.autounit;

import io.leopard.autounit.inject.Inject;
import io.leopard.autounit.inject.InjectContext;
import io.leopard.autounit.inject.InjectImpl;
import io.leopard.autounit.unitdb.ConnectionContext;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动单元测试.
 * 
 * @author 阿海
 *
 */
public class AutoUnit {

	// public static void setPrivateTson(String tson) {
	//
	// }

	public static void assertBean(Class<?> clazz) {
		BeanAssert.assertBean(mock(clazz));
	}

	public static void assertBean(Class<?> clazz, boolean log) {
		BeanAssert.assertBean(mock(clazz), log);
	}

	/**
	 * 回滚测试数据。
	 */
	public static void rollabck() {
		for (Connection conn : ConnectionContext.list()) {
			try {
				conn.rollback();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 清空数据.
	 * 
	 * @param bean
	 */
	public static void clean(Object bean) {
		InjectContext context = injectContextMap.get(bean.hashCode());
		if (context == null) {
			throw new IllegalArgumentException("Bean[" + bean + "]未mock.");
		}
		for (Inject inject : context.listInject()) {
			inject.clean();
		}
	}

	private static Map<Integer, InjectContext> injectContextMap = new HashMap<Integer, InjectContext>();
	private static InjectImpl inject = new InjectImpl();

	private static Map<Integer, Object> beanMap = new HashMap<Integer, Object>();

	public static <T> T mock(Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T bean = (T) beanMap.get(clazz.hashCode());
		if (bean != null) {
			return bean;
		}
		try {
			bean = clazz.newInstance();
		}
		catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		InjectContext context = new InjectContext(bean);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Inject inject2 = inject.inject(bean, field);
			if (inject2 != null) {
				context.add(inject2, field);
			}
		}
		injectContextMap.put(bean.hashCode(), context);
		beanMap.put(clazz.hashCode(), bean);
		return bean;
	}

	public static BeanStubber tson(String tson) {
		return new BeanStubber(tson);
	}

	public static <T> T dao(Class<T> clazz) {
		return dao(clazz, false);
	}

	public static <T> T dao(Class<T> clazz, boolean log) {
		T bean = mock(clazz);
		return dao(bean, log);
	}

	public static <T> T dao(T bean) {
		return dao(bean, false);
	}

	public static <T> T dao(T bean, boolean log) {
		return new BeanStubber("").dao(bean, log);
	}
}
