package org.lodder.subtools.sublibrary;

import java.io.IOException;
import java.io.InputStream;

public class ConfigProperties {

	private static ConfigProperties configProps = null;
	private java.util.Properties prop = new java.util.Properties();
	private InputStream input = null;

	private ConfigProperties() {
		try {
			input = getClass().getResourceAsStream("/config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ConfigProperties getInstance() {
		if (configProps == null)
			configProps = new ConfigProperties();
		return configProps;
	}

	public String getProperty(String key){
		return prop.getProperty(key);
	}
}
