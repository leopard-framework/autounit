package io.leopard.autounit.transaction;

import io.leopard.autounit.AutoUnit;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据源事务测试(用于DAO层的Jdbc、Memcache、Redis、MemDB等实现类的测试).
 * 
 * @author 阿海
 * 
 */
@RunWith(TransactionalRunner.class)
public class TransactionalTests {

	protected Log logger = LogFactory.getLog(this.getClass());

	public TransactionalTests() {
		Class<?> klass = this.getClass();
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Autowired.class) != null) {
				this.inject(klass, field);
			}
			else if (field.getAnnotation(Resource.class) != null) {
				this.inject(klass, field);
			}
		}
	}

	protected void inject(Class<?> klass, Field field) {
		Class<?> clazz = field.getType();
		System.err.println("clazz:" + clazz);
		Object bean = AutoUnit.mock(clazz);
		field.setAccessible(true);
		try {
			field.set(this, bean);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
