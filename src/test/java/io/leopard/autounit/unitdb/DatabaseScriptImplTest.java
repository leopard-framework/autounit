package io.leopard.autounit.unitdb;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseScriptImplTest {

	private DatabaseScriptImpl databaseScriptImpl = new DatabaseScriptImpl();

	@Test
	public void parseTableName() {
		Assert.assertEquals("user", databaseScriptImpl.parseTableName("create table user"));
		Assert.assertEquals("user", databaseScriptImpl.parseTableName("create TABLE user"));
	}

	@Test
	public void generateSql() {
		String sql = this.databaseScriptImpl.generateSql(MemcacheEntity.class, "memcache");
		System.out.println(sql);
	}

	@Test
	public void populate() {
		DataSource dataSource = H2Util.createDataSource("autounit");
		this.databaseScriptImpl.setDataSource(dataSource);
		Assert.assertTrue(databaseScriptImpl.populate(MemcacheEntity.class, "memcache", true));
		System.out.println("ok");
	}
}