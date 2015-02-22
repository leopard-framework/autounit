package io.leopard.autounit.unitdb;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UnitdbH2Impl implements Unitdb {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String queryForString(String sql) {
		return this.queryForObject(sql, String.class);
	}

	@Override
	public String queryForString(String sql, Object... args) {
		return this.queryForObject(sql, args, String.class);
	}

	protected <T> T queryForObject(String sql, Class<T> requiredType) throws DataAccessException {
		try {
			return jdbcTemplate.queryForObject(sql, requiredType);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) throws DataAccessException {
		try {
			return jdbcTemplate.queryForObject(sql, args, requiredType);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Long queryForLong(String sql) {

		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	@Override
	public int update(String sql, Object... args) {
		return this.jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(String sql) {
		return this.jdbcTemplate.update(sql);
	}

}
