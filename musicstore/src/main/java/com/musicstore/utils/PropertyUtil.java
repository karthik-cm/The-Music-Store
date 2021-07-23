package com.musicstore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.musicstore.constants.MusicStoreConstants;

public class PropertyUtil {
	private static Logger logger = Logger.getLogger(PropertyUtil.class);
	
	private static Properties properties = new Properties();
	
	@SuppressWarnings("unused")
	private void loadProperties() {
		String propertyFileName = MusicStoreConstants.MS_PROPERTY_FILE;
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertyFileName);
			properties.load(inputStream);
		}
		catch (IOException e) {
			logger.error("\n\nIOException occurred in loadProperties() :::: PropertyUtil : "+e +"\n");
		}
	}
	
	public static String getPropertyValue(String key) {
		String value = null;
		try {
			boolean containsKey = properties.containsKey(key);
			if(containsKey) {
				value = properties.getProperty(key, null);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getPropertyValue() :::: PropertyUtil : "+e +"\n");
		}
		return value;
	}
}
