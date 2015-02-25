package io.leopard.autounit.unitdb;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

public class H2Util {

	public static String getDir(String database) {
		String tmpdir = System.getProperty("java.io.tmpdir");
		tmpdir = tmpdir.replace("\\", "/");
		System.out.println("tmpdir:" + tmpdir);

		return tmpdir + "autounit/";
	}

	public static DataSource createDataSource(String database) {
		String jdbcUrl = "jdbc:h2:" + getDir(database) + database;
		JdbcDataSource dataSource = new JdbcDataSource() {
			private static final long serialVersionUID = 1L;

			@Override
			public Connection getConnection() throws SQLException {
				Connection conn = super.getConnection();
				conn.setAutoCommit(false);
				System.err.println("conn:" + conn);
				// new Exception().printStackTrace();
				return conn;
			};

		};
		dataSource.setURL(jdbcUrl);
		dataSource.setUser("sa");
		dataSource.setPassword("");

		return dataSource;

		// ComboPooledDataSource dataSource = new ComboPooledDataSource();
		//
		// // jdbcUrl += ";autocommit=true";
		//
		// // String jdbcUrl = "jdbc:h2:tcp://localhost/mem:" + database;
		// dataSource.setJdbcUrl(jdbcUrl);// MVCC=true
		// try {
		// dataSource.setDriverClass("org.h2.Driver");
		// }
		// catch (PropertyVetoException e) {
		// throw new RuntimeException(e.getMessage(), e);
		// }
		// dataSource.setUser("sa");
		// dataSource.setPassword("");
		//
		// {
		// dataSource.setInitialPoolSize(1);
		// dataSource.setMinPoolSize(1);
		// dataSource.setMaxPoolSize(5);
		// dataSource.setAcquireIncrement(1);
		// // dataSource.setAcquireRetryAttempts(1);
		// // dataSource.setMaxIdleTime(7200);
		// // dataSource.setMaxStatements(0);
		// }
		//
		// // if (true) {
		// // return dataSource;
		// // }
		//
		// return new DataSourceProxy(dataSource) {
		// @Override
		// public Connection getConnection() throws SQLException {
		// Connection conn = super.getConnection();
		// conn.setAutoCommit(false);
		// System.err.println("conn:" + conn);
		// new Exception().printStackTrace();
		// return conn;
		// }
		// };
	}
}
