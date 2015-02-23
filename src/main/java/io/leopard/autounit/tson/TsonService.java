package io.leopard.autounit.tson;

import java.util.Map;

public interface TsonService {
	<T> T toObject(Class<T> clazz, Map<String, Object> data) throws Exception;

	<T> T toObject(Class<T> clazz, String tson) throws Exception;

}
