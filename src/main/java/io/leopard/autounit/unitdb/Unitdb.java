package io.leopard.autounit.unitdb;

import java.util.List;
import java.util.Map;

/**
 * 数据库操作接口.
 * 
 * @author 阿海
 *
 */
public interface Unitdb {

	String queryForString(String sql);

	String queryForString(String sql, Object... args);

	Integer queryForInt(String sql, Object... args);

	Long queryForLong(String sql);

	int update(String sql, Object... args);

	int update(String sql);

	/**
	 * 根据sql查询数据，返回elementType参数对象.
	 * 
	 * @param sql
	 * @param elementType
	 *            Class类型
	 * @param params
	 *            参数列表
	 * @return 返回查询的单个对象
	 */
	<T> T query(String sql, Class<T> elementType, Object... params);

	/**
	 * 根据sql查询数据，返回String的List.
	 * 
	 * @param sql
	 *            sql
	 * @param params
	 *            参数列表
	 * @return 查询的数据
	 */
	List<String> queryForStrings(String sql, Object... params);

	/**
	 * 根据sql查询数据.
	 * 
	 * @param sql
	 *            查询数据的sql
	 * @param elementType
	 *            数据对应的model对象
	 * @param params
	 *            参数列表
	 * @return 查询的数据
	 */
	<T> List<T> queryForList(String sql, Class<T> elementType, Object... params);

	List<Map<String, Object>> queryForMaps(String sql);
}
