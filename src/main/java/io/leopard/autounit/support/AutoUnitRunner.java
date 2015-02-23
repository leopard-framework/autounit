package io.leopard.autounit.support;

import io.leopard.autounit.AutoUnit;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;

public class AutoUnitRunner extends BlockJUnit4ClassRunner {

	public AutoUnitRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Object createTest() throws Exception {
		Object testInstance = super.createTest();
		Class<?> clazz = super.getTestClass().getJavaClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!this.isBean(field)) {
				continue;
			}
			field.setAccessible(true);
			Object value = field.get(testInstance);
			if (value != null) {
				continue;
			}
			value = AutoUnit.mock(field.getType());
			field.set(testInstance, value);
		}
		return testInstance;
	}

	protected boolean isBean(Field field) {
		if (field.getAnnotation(Autowired.class) != null) {
			return true;
		}
		if (field.getAnnotation(Resource.class) != null) {
			return true;
		}
		return false;
	}

	// // 拦截每一个方法的 Before 事件
	// protected Statement withBefores(final FrameworkMethod method, Object target, final Statement statement) {
	// final Statement junitStatement = super.withBefores(method, target, statement);
	// return new Statement() {
	// @Override
	// public void evaluate() throws Throwable {
	// System.out.println("Before before method: " + method.getName());
	// junitStatement.evaluate();
	// System.out.println("After before method: " + method.getName());
	// }
	// };
	// }

	@Override
	protected Statement methodBlock(FrameworkMethod method) {
		System.err.println("methodBlock start:" + method.getName());
		Statement statement = super.methodBlock(method);
		System.err.println("methodBlock end:" + method.getName());
		return statement;

	}
}
