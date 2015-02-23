package io.leopard.autounit.tson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldValueImpl implements FieldValue {

	private List<FieldValue> list = new ArrayList<FieldValue>();

	public FieldValueImpl() {
		list.add(new FieldValueIntegerImpl());

		list.add(new FieldValueLongImpl());
		list.add(new FieldValueStringImpl());
		list.add(new FieldValueDateImpl());

		list.add(new FieldValueFloatImpl());
		list.add(new FieldValueDoubleImpl());
		list.add(new FieldValueBooleanImpl());

	}

	@Override
	public Object get(Class<?> clazz, String name) throws Exception {
		{
			Object value = this.getSimpleValue(clazz, name);
			if (value != null) {
				return value;
			}
		}

		Object bean = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// System.out.println("field:" + field.getName() + " type:" + field.getType());
			Object value = this.get(field.getType(), field.getName());

			field.setAccessible(true);
			field.set(bean, value);
		}
		return bean;
	}

	protected Object getSimpleValue(Class<?> clazz, String name) throws Exception {
		for (FieldValue field : list) {
			Object value = field.get(clazz, name);
			if (value != null) {
				return value;
			}
		}
		// System.out.println("getSimpleValue:" + name + " IS NULL.");
		return null;
	}

}
