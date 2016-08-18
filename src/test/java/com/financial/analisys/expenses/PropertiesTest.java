package com.financial.analisys.expenses;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	Properties properties;

	private void loadProperties() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(
					"src/test/resources/properties.properties")));
		} catch (Exception e) {

		}
	}

	@Test
	public void testPropertiesLoad() {
		loadProperties();

		String property = properties.getProperty("properties.test.hola");
		assertEquals(property, "../Hola");
		property = properties.getProperty("properties.test.mundo");
		assertEquals(property, "../Mundo");
	}
}