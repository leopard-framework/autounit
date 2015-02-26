package io.leopard.autounit.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AutoUnitConfigImpl implements AutoUnitConfig {
	private AutoUnitConfigFileImpl autoUnitConfigFileImpl = new AutoUnitConfigFileImpl();

	@Override
	public List<Properties> list() throws IOException {
		return autoUnitConfigFileImpl.list();
	}

	@Override
	public List<String> listRule() throws IOException {
		return autoUnitConfigFileImpl.listRule();
	}

	@Override
	public Map<String, String> mapField() throws IOException {
		return autoUnitConfigFileImpl.mapField();
	}

	@Override
	public List<String> listIntegrationRunnable() throws IOException {
		return autoUnitConfigFileImpl.listIntegrationRunnable();
	}

}
