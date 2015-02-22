package io.leopard.autounit.unitdb;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class UnitdbH2Impl implements Unitdb {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String queryForString(String sql) {
		return jdbcTemplate.queryForObject(sql, String.class);
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
