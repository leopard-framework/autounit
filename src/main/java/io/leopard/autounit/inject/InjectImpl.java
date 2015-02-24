package io.leopard.autounit.inject;

import io.leopard.autounit.config.AutoUnitConfigImpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InjectImpl implements Inject {

	private List<Inject> list = new ArrayList<Inject>();

	public InjectImpl() {
		List<Inject> ruleList = this.listCustomRule();
		// System.out.println("ruleList:" + ruleList);
		list.addAll(ruleList);
		this.list.add(new InjectJdbcImpl());
	}

	@Override
	public Inject inject(Object bean, Field field) {
		for (Inject inject : list) {
			Inject inject2 = inject.inject(bean, field);
			if (inject2 != null) {
				return inject2;
			}
		}
		return null;
	}

	/**
	 * 获取所有自定义规则
	 * 
	 * @return
	 */
	protected List<Inject> listCustomRule() {
		List<String> classNameList;
		try {
			classNameList = new AutoUnitConfigImpl().listRule();
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
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

	@Override
	public boolean clean() {
		throw new UnsupportedOperationException("Not Impl.");
	}

}
