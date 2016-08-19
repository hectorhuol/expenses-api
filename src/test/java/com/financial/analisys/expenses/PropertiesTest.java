package com.financial.analisys.expenses;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	Properties properties;

	private void loadProperties() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(new File(
				"src/test/resources/properties.properties")));
	}

	@Test
	public void testPropertiesLoad() throws FileNotFoundException, IOException {
		loadProperties();

		String property = properties.getProperty("properties.test.hola");
		assertEquals(property, "../Hola");
		property = properties.getProperty("properties.test.mundo");
		assertEquals(property, "../Mundo");
	}
}