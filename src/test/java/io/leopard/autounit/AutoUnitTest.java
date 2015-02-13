package io.leopard.autounit;

import org.junit.Test;

public class AutoUnitTest {

	private UserDao userDao = new UserDao();

	@Test
	public void dao() {
		AutoUnit.dao(userDao).get(1);
	}

}