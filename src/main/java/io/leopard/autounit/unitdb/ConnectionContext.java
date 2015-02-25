package io.leopard.autounit.unitdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class ConnectionContext {

	private static List<Connection> list = new ArrayList<Connection>();

	public static DataSource register(DataSource dataSource) {
		Connection conn;
		try {
			conn = dataSource.getConnection();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		list.add(conn);
		return new SingleConnectionDataSource(conn, false);
	}

	public static List<Connection> list() {
		return list;
	}
}
