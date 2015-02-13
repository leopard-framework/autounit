package io.leopard.autounit;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * 读取classpath下 的所有/autounit.properties.
 * 
 * @author 阿海
 *
 */
public class AutounitProperties {

	public static List<Properties> list() throws IOException {
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
				return (order1 - order2);
			}
		});
		System.err.println("list:" + list);

		return list;
	}

	private static int parseOrder(String order) {
		if (order == null) {
			return Integer.MAX_VALUE;
		}
		return Integer.parseInt(order);
	}

	private static List<InputStream> listAll() throws IOException {
		Enumeration<URL> e = AutounitProperties.class.getClassLoader().getResources("autounit.properties");
		List<InputStream> list = new ArrayList<InputStream>();
		while (e.hasMoreElements()) {
			URL url = e.nextElement();
			System.out.println("url:" + url);
			list.add(toInputStream(url));
		}
		return list;
	}

	private static InputStream toInputStream(URL url) throws IOException {
		URLConnection conn = url.openConnection();
		try {
			return conn.getInputStream();
		}
		catch (IOException e) {
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
			throw e;
		}
	}
}
