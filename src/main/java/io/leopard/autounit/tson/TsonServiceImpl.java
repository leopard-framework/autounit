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

	protected static Object toObject(String value, Class<?> type) {
		if (type.equals(String.class)) {
			return value;
		}
		else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			return Boolean.parseBoolean(value);
		}
		else if (type.equals(int.class) || type.equals(Integer.class)) {
			return Integer.parseInt(value);
		}
		else if (type.equals(long.class) || type.equals(Long.class)) {
			return Long.parseLong(value);
		}
		else if (type.equals(float.class) || type.equals(Float.class)) {
			return Float.parseFloat(value);
		}
		else if (type.equals(double.class) || type.equals(Double.class)) {
			return Double.parseDouble(value);
		}
		else if (type.equals(Date.class)) {
			return parseDate(value);
		}
		throw new RuntimeException("未知类型[" + type.getName() + "].");
		// return value;
	}

	protected static long toTime(String str) {
		if (str.endsWith("ms")) {
			return Long.parseLong(str.replace("ms", ""));
		}
		else if (str.endsWith("s")) {
			return Long.parseLong(str.replace("s", "")) * 1000;
		}
		else if (str.endsWith("m")) {
			return Long.parseLong(str.replace("m", "")) * 60 * 1000;
		}
		else if (str.endsWith("h")) {
			return Long.parseLong(str.replace("h", "")) * 60 * 60 * 1000;
		}
		else if (str.endsWith("D")) {
			return Long.parseLong(str.replace("D", "")) * 60 * 60 * 1000 * 24;
		}
		throw new RuntimeException("非法时间[" + str + "].");
	}

	protected static Date parseDate(String str) {
		if ("now".equalsIgnoreCase(str)) {
			return new Date();
		}
		if (str.startsWith("now+")) {
			long time = System.currentTimeMillis() + toTime(str.substring(4));
			return new Date(time);
		}
		if (str.startsWith("now-")) {
			long time = System.currentTimeMillis() - toTime(str.substring(4));
			return new Date(time);
		}
		// TODO ahai 未完整实现
		// if (DateTime.isDate(str)) {
		// return DateUtil.toDate(str + " 00:00:00");
		// }
		return new Date(Long.parseLong(str));
	}
}
