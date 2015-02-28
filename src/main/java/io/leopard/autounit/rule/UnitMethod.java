package io.leopard.autounit.rule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class UnitMethod {

	private Object bean;
	private Method method;
	private String[] names;
	private Object[] args;
	private Map<String, String> tson;

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Map<String, String> getTson() {
		return tson;
	}

	public void setTson(Map<String, String> tson) {
		this.tson = tson;
	}

	public String getName() {
		return this.method.getName();
	}

	public String toGenericString() {
		return this.method.toGenericString();
	}

	public Object invoke() {
		return this.invoke(args);
	}

	public Object invoke(Object... args) {
		try {
			return this.method.invoke(bean, args);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
