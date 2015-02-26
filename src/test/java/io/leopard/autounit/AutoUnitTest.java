package io.leopard.autounit;

import org.junit.Test;

public class AutoUnitTest {

	@Test
	public void dao() {
		AutoUnit.dao(UserDao.class).get(1);
	}

}