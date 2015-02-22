package io.leopard.autounit.unitdb;

import java.io.RandomAccessFile;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

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

		String tmpdir = System.getProperty("java.io.tmpdir");
		System.out.println("tmpdir:" + tmpdir);
		DataSource dataSource = H2Util.createDataSource("autounit");
		this.databaseScriptImpl.setDataSource(dataSource);
		databaseScriptImpl.populate(MemcacheEntity.class, "memcache");
		System.out.println("ok");
		((ComboPooledDataSource) dataSource).close(true);
	}

}