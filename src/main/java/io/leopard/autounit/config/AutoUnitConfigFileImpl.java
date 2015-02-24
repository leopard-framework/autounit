package io.leopard.autounit.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

/**
 * 读取classpath下 的所有/autounit.properties.
 * 
 * @author 阿海
 *
 */
public class AutoUnitConfigFileImpl implements AutoUnitConfig {

	@Override
	public List<Properties> list() throws IOException {
		List<Properties> list = new ArrayList<Properties>();
		List<InputStream> inputList = listAll();

		for (InputStream input : inputList) {
			Properties props = new Properties();
			props.load(input);
			input.close();
			list.add(props);
		}
		Collections.sort(list, new Comparator<Properties>() {
			@Override
			public int compare(Properties o1, Properties o2) {
				int order1 = parseOrder((String) o1.get("order"));
				int order2 = parseOrder((String) o2.get("order"));
				return (order2 - order1);
			}
		});
		// System.err.println("list:" + list);

		return list;
	}

	private int parseOrder(String order) {
		if (order == null) {
			return Integer.MAX_VALUE;
		}
		return Integer.parseInt(order);
	}

	private List<InputStream> listAll() throws IOException {
		Enumeration<URL> urls = this.getClass().getClassLoader().getResources("autounit.properties");
		List<InputStream> list = new ArrayList<InputStream>();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			// System.err.println("url:" + url.toString());
			URLConnection conn = url.openConnection();
			try {
				list.add(conn.getInputStream());
			}
			catch (IOException e) {
				if (conn instanceof HttpURLConnection) {
					((HttpURLConnection) conn).disconnect();
				}
				throw e;
			}
		}
		return list;
	}

	@Override
	public List<String> listRule() throws IOException {
		List<String> classNameList = new ArrayList<String>();
		for (Properties props : list()) {
			TreeMap<Integer, String> map = new TreeMap<Integer, String>();
			for (Entry<Object, Object> entry : props.entrySet()) {
				String key = (String) entry.getKey();
				int order;
				if ("inject".equals(key)) {
					order = Integer.MAX_VALUE;
				}
				else if (key.startsWith("inject.")) {
					String str = key.replaceFirst("^inject\\.([0-9]+)$", "$1");
					if (str.equals(key)) {
						continue;
					}
					else {
						order = Integer.parseInt(str);
					}
				}
				else {
					continue;
				}
				map.put(order, ((String) entry.getValue()).trim());
			}
			classNameList.addAll(map.values());
		}
		return classNameList;
	}

	@Override
	public Map<String, String> mapField() throws IOException {
		Map<String, String> fieldMap = new HashMap<String, String>();
		for (Properties props : list()) {
			for (Entry<Object, Object> entry : props.entrySet()) {
				String key = (String) entry.getKey();
				if (key.startsWith("field.")) {
					// System.err.println(key + ":" + entry.getValue());
					String fieldName = key.replaceFirst("^field\\.([0-9a-zA-Z_\\-]+)$", "$1");
					fieldMap.put(fieldName, ((String) entry.getValue()).trim());
				}
				else {
					continue;
				}
			}
		}
		return fieldMap;
	}
}
