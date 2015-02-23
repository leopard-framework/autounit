package io.leopard.autounit.tson;

/**
 * 默认值
 * 
 * @author 阿海
 *
 */
public interface FieldValue {

	Object get(Class<?> clazz, String name) throws Exception;
}
