package io.leopard.autounit.unitdb;

public interface DatabaseScript {

	boolean populate(Class<?> entityClazz, String tableName);

	boolean populate(Class<?> entityClazz, String tableName, boolean dropTable);

	boolean populate(String sql);

	boolean populate(String sql, boolean dropTable);
}
