package io.leopard.autounit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.h2.util.IOUtils;

public class FileUtils {

	public static String readToString(File file) {
		try {
			return IOUtils.readStringAndClose(new FileReader(file), -1);
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String toString(InputStream input) {
		try {
			return IOUtils.readStringAndClose(new InputStreamReader(input), -1);
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void write(File file, String content) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
