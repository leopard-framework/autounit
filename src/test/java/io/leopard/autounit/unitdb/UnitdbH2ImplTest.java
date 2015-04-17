package io.leopard.autounit.unitdb;

import javax.sql.DataSource;

import org.junit.Test;

public class UnitdbH2ImplTest {

	@Test
	public void queryForString() {
		UnitdbH2Impl unitdb = new UnitdbH2Impl();
		DataSource dataSource = H2Util.createDataSource("autounit");
		unitdb.setDataSource(dataSource);

		
		String user = unitdb.queryForString("select user()");
		// String user = unitdb.queryForString("select value from memcache");
		System.out.println("user:" + user);
	}

}