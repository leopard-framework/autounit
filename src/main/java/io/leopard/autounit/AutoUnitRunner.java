package io.leopard.autounit;

import javax.sql.DataSource;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class AutoUnitRunner extends BlockJUnit4ClassRunner {

	public AutoUnitRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Statement methodBlock(FrameworkMethod method) {
		DataSource dataSource = null;
		System.err.println("methodBlock start:" + method.getName());
		Statement statement = super.methodBlock(method);
		System.err.println("methodBlock end:" + method.getName());
		return statement;

	}
}
