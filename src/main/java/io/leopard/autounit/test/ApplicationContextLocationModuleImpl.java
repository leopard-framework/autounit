package io.leopard.autounit.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 按模块解析.
 * 
 * @author 阿海
 *
 */
public class ApplicationContextLocationModuleImpl implements ApplicationContextLocation {

	private String[] locations = { "/applicationContext.xml", "/applicationContext-service.xml", "/applicationContext-dao.xml" };

	@Override
	public String[] get() {
		for (String path : locations) {
			Resource resource = new ClassPathResource(path);
			if (resource.exists()) {
				return new String[] { "/leopard/applicationContext.xml", path };
			}
		}
		return null;
	}

}
