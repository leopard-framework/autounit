package io.leopard.autounit.inject;

import io.leopard.autounit.AutounitProperties;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

public class InjectImpl implements Inject {

	private List<Inject> list = new ArrayList<Inject>();

	public InjectImpl() {
		List<Inject> ruleList = this.listCustomRule();
		// System.out.println("ruleList:" + ruleList);
		list.addAll(ruleList);
		this.list.add(new InjectJdbcImpl());
	}

	@Override
	public boolean inject(Object bean, Field field) {
		for (Inject inject : list) {
			boolean success = inject.inject(bean, field);
			if (success) {
				return true;
			}
		}
		return false;
	}

	protected List<Inject> listCustomRule() {
		List<Properties> propList;
		try {
			propList = AutounitProperties.list();
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		List<String> classNameList = new ArrayList<String>();
		for (Properties props : propList) {
			classNameList.addAll(this.listCustomRule(props));
		}
		List<Inject> ruleList = new ArrayList<Inject>();
		for (String className : classNameList) {
			try {
				Class<?> clazz = Class.forName(className);
				Inject inject = (Inject) clazz.newInstance();
				ruleList.add(inject);
			}
			catch (ClassNotFoundException e) {
				throw new RuntimeException("ClassNotFoundException:" + e.getMessage(), e);
			}
			catch (InstantiationException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			catch (IllegalAccessException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return ruleList;
	}

	protected Collection<String> listCustomRule(Properties props) {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (Entry<Object, Object> entry : props.entrySet()) {
			String key = (String) entry.getKey();
			int order;
			if ("inject".equals(key)) {
				order = Integer.MAX_VALUE;
			}
			else if (key.startsWith("inject.")) {
				String str = key.replaceFirst("^inject\\.([0-9]+)$", "$1");
				if (str.equals(key)) {
					continue;
				}
				else {
					order = Integer.parseInt(str);
				}
			}
			else {
				continue;
			}
			map.put(order, (String) entry.getValue());
		}
		return map.values();
	}
}
