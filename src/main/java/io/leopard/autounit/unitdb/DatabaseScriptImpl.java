package io.leopard.autounit.unitdb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.StringUtils;

public class DatabaseScriptImpl implements DatabaseScript {
	private DataSource dataSource;

	public DatabaseScriptImpl() {

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean populate(Class<?> entityClazz, String tableName) {
		return this.populate(entityClazz, tableName, false);
	}

	// CREATE TABLE `user` (
	// `uid` int(10) unsigned NOT NULL,
	// `nickname` varchar(30) NOT NULL DEFAULT '',
	// `posttime` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
	// PRIMARY KEY (`uid`)
	// );

	protected boolean isKey(Field field) {
		XmlID id = field.getAnnotation(XmlID.class);
		return (id != null);
	}

	protected String toDataType(Field field) {
		Class<?> type = field.getType();
		if (type.equals(int.class) || type.equals(Integer.class)) {
			return "int(10) unsigned NOT NULL 0,";
		}
		else if (type.equals(long.class) || type.equals(Long.class)) {
			return "int(11) unsigned NOT NULL 0,";
		}
		else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			return "tinyint unsigned NOT NULL 0,";
		}
		else if (type.equals(String.class)) {
			return "varchar(255) NOT NULL DEFAULT '',";
		}
		else if (type.equals(Date.class)) {
			return "datetime NOT NULL DEFAULT '1970-01-01 00:00:00',";
		}
		throw new IllegalArgumentException("未知数据类型.");
	}

	protected String generateSql(Class<?> entityClazz, String tableName) {
		Field[] fields = entityClazz.getDeclaredFields();

		List<String> keyList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("create table ").append(tableName).append(" (\n");
		for (Field field : fields) {
			String fieldName = field.getName();
			String dataType = this.toDataType(field);
			sb.append("\t`" + fieldName + "` ").append(dataType).append("\n");
			if (this.isKey(field)) {
				keyList.add("`" + fieldName + "`");
			}
		}

		sb.append("\tPRIMARY KEY (" + StringUtils.collectionToDelimitedString(keyList, ",") + ")\n");
		sb.append(");\n");
		return sb.toString();
	}

	@Override
	public boolean populate(Class<?> entityClazz, String tableName, boolean dropTable) {
		String sql = this.generateSql(entityClazz, tableName);
		return this.populate(sql, dropTable);
	}

	@Override
	public boolean populate(String sql) {
		return this.populate(sql, false);
	}

	/**
	 * 解析表名称
	 * 
	 * @param sql
	 * @return
	 */
	protected String parseTableName(String sql) {
		sql = sql.replace("`", "");
		Pattern p = Pattern.compile("create table\\s+([a-zA-Z0-9\\-_]+)", Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(sql);
		if (m.find()) {
			return m.group(1);
		}
		throw new IllegalArgumentException("解析表名出错.");
	}

	@Override
	public boolean populate(String sql, boolean dropTable) {
		Resource scripts = new ByteArrayResource(sql.getBytes());
		DatabasePopulator populator = new ResourceDatabasePopulator(scripts);
		DatabasePopulatorUtils.execute(populator, dataSource);
		return true;
	}

}
