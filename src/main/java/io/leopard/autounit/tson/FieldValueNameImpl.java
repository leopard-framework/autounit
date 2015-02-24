package io.leopard.autounit.tson;

import io.leopard.autounit.config.AutoUnitConfigImpl;

import java.io.IOException;
import java.util.Map;

/**
 * 名称实现.
 * 
 * @author 阿海
 *
 */
public class FieldValueNameImpl implements FieldValue {

	private Map<String, String> map = null;

	public FieldValueNameImpl() {
		try {
			map = new AutoUnitConfigImpl().mapField();
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public Object get(Class<?> clazz, String name) {
		String value = map.get(name);
		if (value == null) {
			return null;
		}
		return TsonDataType.toObject(value, clazz);
	}

}
