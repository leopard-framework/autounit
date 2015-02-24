package io.leopard.autounit.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface AutoUnitConfig {

	List<Properties> list() throws IOException;

	List<String> listRule() throws IOException;

	Map<String, String> mapField() throws IOException;

}
