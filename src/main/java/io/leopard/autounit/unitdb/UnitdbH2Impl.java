package io.leopard.autounit.unitdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

	@Override
	public Integer queryForInt(String sql, Object... args) {
		return this.queryForObject(sql, args, Integer.class);
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

	@Override
	public <T> T query(String sql, Class<T> elementType, Object... params) {
		try {
			return this.jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<T>(elementType));
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<String> queryForStrings(String sql, Object... params) {
		List<String> list = jdbcTemplate.query(sql, params, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int index) {
				try {
					return rs.getString(1);
				}
				catch (SQLException e) {
					throw new InvalidResultSetAccessException(e);
				}
			}
		});
		return list;
	}

	@Override
	public <T> List<T> queryForList(String sql, Class<T> elementType, Object... params) {
		try {
			return this.jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(elementType));
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> queryForMaps(String sql) {
		try {
			return this.jdbcTemplate.queryForList(sql);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
