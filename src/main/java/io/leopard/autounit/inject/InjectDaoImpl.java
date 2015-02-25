package io.leopard.autounit.inject;

import io.leopard.autounit.AutoUnit;
import io.leopard.autounit.PackageUtil;

import java.lang.reflect.Field;
import java.util.Set;

public class InjectDaoImpl extends AbstractInject {

	private Object daoBean;

	@Override
	public Inject inject(Object bean, Field field) {
		Class<?> type = field.getType();
		String typeClassName = type.getName();
		if (!(typeClassName.endsWith("Dao"))) {
			return null;
		}

		Class<?> clazz;
		try {
			clazz = this.findClass(type, field.getName());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("type:" + type + " name:" + field.getName() + " " + clazz.getName());

		this.daoBean = AutoUnit.mock(clazz);
		super.setFieldValue(bean, field, this.daoBean);
		return this;
	}

	protected Class<?> findClass(Class<?> type, String fieldName) throws ClassNotFoundException {
		String basePackage = type.getPackage().getName();
		Set<Class<?>> classes = PackageUtil.getClasses(basePackage, true);
		String end = "." + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		for (Class<?> clazz : classes) {
			// System.out.println("clazz:" + clazz.getName());
			if (clazz.getName().endsWith(end)) {
				return clazz;
			}
		}
		throw new ClassNotFoundException(basePackage + "***" + end);
	}

	@Override
	public boolean clean() {
		return false;
	}

}
