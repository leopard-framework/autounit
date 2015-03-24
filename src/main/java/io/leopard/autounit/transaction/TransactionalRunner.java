package io.leopard.autounit.transaction;

import io.leopard.autounit.AutoUnit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class TransactionalRunner extends BlockJUnit4ClassRunner {

	public TransactionalRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		super.runChild(method, notifier);
		System.err.println("method:" + method.getMethod().toGenericString());
		AutoUnit.rollabck();
	}

}
