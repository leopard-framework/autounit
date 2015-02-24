package io.leopard.autounit.inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InjectContext {

	protected Object bean;
	private List<Inject> injectList = new ArrayList<Inject>();

	public InjectContext(Object bean) {
		this.bean = bean;
	}

	public void add(Inject inject, Field field) {
		injectList.add(inject);
	}

	public List<Inject> listInject() {
		return injectList;
	}

}
