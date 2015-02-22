package io.leopard.autounit.unitdb;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class H2Util {

	public static DataSource createDataSource(String database) {

		String tmpdir = System.getProperty("java.io.tmpdir");
		tmpdir = tmpdir.replace("\\", "/");
		System.out.println("tmpdir:" + tmpdir);

		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		String jdbcUrl = "jdbc:h2:" + tmpdir + "autounit/" + database;
		// String jdbcUrl = "jdbc:h2:tcp://localhost/mem:" + database;
		dataSource.setJdbcUrl(jdbcUrl);// MVCC=true
		try {
			dataSource.setDriverClass("org.h2.Driver");
		}
		catch (PropertyVetoException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		dataSource.setUser("sa");
		dataSource.setPassword("");

		{
			dataSource.setInitialPoolSize(1);
			dataSource.setMinPoolSize(1);
			dataSource.setMaxPoolSize(3);
			dataSource.setAcquireIncrement(1);
			// dataSource.setAcquireRetryAttempts(1);
			// dataSource.setMaxIdleTime(7200);
			// dataSource.setMaxStatements(0);
		}
		return dataSource;
	}

}
