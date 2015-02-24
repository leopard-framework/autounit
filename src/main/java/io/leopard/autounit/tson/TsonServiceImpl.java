package io.leopard.autounit.tson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TsonServiceImpl implements TsonService {

	private FieldValue fieldValue = new FieldValueImpl();

	@Override
	public <T> T toObject(Class<T> clazz, Map<String, String> data) throws Exception {
		@SuppressWarnings("unchecked")
		T bean = (T) fieldValue.get(clazz, null);
		for (Entry<String, String> entry : data.entrySet()) {
			String key = entry.getKey();
			Field field;
			try {
				field = clazz.getDeclaredField(key);
			}
			catch (NoSuchFieldException e) {
				throw new NoSuchFieldException(e.getMessage() + " clazz:" + clazz.getName());
			}
			field.setAccessible(true);
			Object value = TsonDataType.toObject(entry.getValue(), field.getType());
			field.set(bean, value);
		}
		return bean;
	}

	public static String[] split(String str, Character separator) {
		if (str.indexOf('\'') == -1 && str.indexOf('"') == -1) {
			return str.split(separator.toString());
		}
		List<String> list = new ArrayList<String>();
		char[] chars = str.toCharArray();
		int prefix = 0;
		boolean hasQuotes = false;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '\'' || chars[i] == '"') {
				hasQuotes = !hasQuotes;
				continue;
			}
			if (chars[i] == separator && !hasQuotes) {
				String word = str.substring(prefix, i);
				// System.out.println("prefix:" + prefix + " i:" + i + " word:" + word);
				list.add(word);
				prefix = i + 1;
			}
		}
		if (prefix < chars.length) {
			list.add(str.substring(prefix));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	@Override
	public Map<String, String> toMap(String tson) {
		tson = tson.replace("{", "");
		tson = tson.replace("}", "");
		Map<String, String> data = new HashMap<String, String>();
		if (tson.length() == 0) {
			return data;
		}
		String[] strs = split(tson, ',');
		for (String str : strs) {
			String[] block = split(str, ':');
			String fieldName = block[0];
			String value = block[1].replace("\"", "");
			data.put(fieldName, value);
		}
		return data;
	}

	@Override
	public <T> T toObject(Class<T> clazz, String tson) throws Exception {
		Map<String, String> data = this.toMap(tson);
		return this.toObject(clazz, data);
	}

}
