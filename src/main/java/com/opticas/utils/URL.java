package com.opticas.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class URL {
	Logger logger = Logger.getLogger("");

	public String getValue(String propertie) {
		logger.info("in get value url param:" + propertie);
		String value = "";
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			entrada = new FileInputStream("url.properties");
			propiedades.load(entrada);
			value = propiedades.getProperty(propertie);
			logger.info("value form properties" + value);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {

					logger.warning("error in get url:" + propertie);
				}
			}
		}
		return value;
	}
}
