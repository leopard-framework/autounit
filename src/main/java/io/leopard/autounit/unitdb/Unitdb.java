package io.leopard.autounit.unitdb;

/**
 * 数据库操作接口.
 * 
 * @author 阿海
 *
 */
public interface Unitdb {

	String queryForString(String sql);

	Long queryForLong(String sql);

	int update(String sql, Object... args);

	int update(String sql);
}
