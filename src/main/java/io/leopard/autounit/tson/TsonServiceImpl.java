package io.leopard.autounit.tson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TsonServiceImpl implements TsonService {

	private FieldValue fieldValue = new FieldValueImpl();

	@Override
	public <T> T toObject(Class<T> clazz, Map<String, Object> data) throws Exception {
		@SuppressWarnings("unchecked")
		T bean = (T) fieldValue.get(clazz, null);

		for (Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			Field field = clazz.getField(key);
			field.setAccessible(true);
			field.set(bean, value);
		}
		return bean;
	}

	@Override
	public <T> T toObject(Class<T> clazz, String tson) throws Exception {
		tson = tson.replace("{", "");
		tson = tson.replace("}", "");

		Map<String, Object> data = new HashMap<String, Object>();

		return this.toObject(clazz, data);
	}

}
