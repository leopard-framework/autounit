package io.leopard.autounit.rule;

import org.junit.Assert;

public class MethodTemplateImpl implements MethodTemplate {

	private MethodHuman methodHumanAddImpl = new MethodHumanAddImpl();

	@Override
	public Object add(UnitMethod unitMethod, boolean log) throws NoSuchMethodException {
		Object result = methodHumanAddImpl.invoke(unitMethod, log);
		boolean success = (Boolean) result;
		Assert.assertTrue("调用add方法没有返回true.", success);
		return success;
	}

	@Override
	public Object get(UnitMethod unitMethod, boolean log) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object delete(UnitMethod unitMethod, boolean log) {
		// TODO Auto-generated method stub
		return false;
	}

}
