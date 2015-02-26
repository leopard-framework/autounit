package io.leopard.autounit.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextLoader;
import org.springframework.util.StringUtils;

/**
 * 
 * @author 阿海
 * 
 */
public class TestContextLoader implements ContextLoader {
	/** 第一个入口 */
	private static final String ENTRY_FIRST = "/integrationTest.xml";

	@Override
	public String[] processLocations(Class<?> clazz, String... locations) {
		return locations;
	}

	@Override
	public ApplicationContext loadContext(String... locations) throws Exception {
		// new HostLeiImpl();

		String[] files = getFiles(locations);
		// files = ArrayUtil.insertFirst(files, "/leopard-test/annotation-config.xml");
		files = StringUtils.addStringToArray(files, "/leopard-test/annotation-config.xml");

		return new ClassPathXmlApplicationContext(files);
	}

	protected String[] getFiles(String... locations) {
		if (locations.length > 0) {
			return locations;
		}
		ClassPathResource resource = new ClassPathResource(ENTRY_FIRST);
		System.err.println("resource.exists():" + resource.exists() + " ENTRY_FIRST:" + ENTRY_FIRST);
		String filename;
		if (resource.exists()) {
			filename = ENTRY_FIRST;
		}
		else {
			filename = this.getModuleApplicationContextPath();
			System.err.println("filename:" + filename);
		}
		return new String[] { filename };
	}

	private String[] defaultModules = { "web", "service", "dao" };

	protected String getModuleApplicationContextPath() {
		ModuleParserLei moduleParserLei = new ModuleParserLeiImpl();
		if (moduleParserLei.isSingleModule()) {
			return "/leopard-test/applicationContext-web.xml";
		}
		{
			String moduleName = moduleParserLei.getModuleName();
			String path = "/leopard-test/applicationContext-" + moduleName + ".xml";
			Resource resource = new ClassPathResource(path);
			if (resource.exists()) {
				return path;
			}
		}

		String contextPath = this.getDefaultContextPath();
		if (contextPath != null) {
			return contextPath;
		}

		return "/leopard-test/applicationContext-default.xml";
	}

	protected String getDefaultContextPath() {
		for (String moduleName : defaultModules) {
			String contextPath;
			if ("web".equals(moduleName)) {
				contextPath = "/applicationContext.xml";
			}
			else {
				contextPath = "/applicationContext-" + moduleName + ".xml";
			}
			Resource resource = new ClassPathResource(contextPath);
			if (resource.exists()) {
				return contextPath;
			}
		}
		return null;
	}
}
