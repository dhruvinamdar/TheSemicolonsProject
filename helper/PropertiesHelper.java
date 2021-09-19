package com.product.helper;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
	
		private final Properties dbProps;
		public PropertiesHelper()
		{
			dbProps = new Properties();
			try {
				dbProps.load(this.getClass().getClassLoader().getResourceAsStream("jdbcprops.properties"));
		} catch(IOException e)
			{
			e.printStackTrace();
			}
		}
		public String getProperty(String key)
		{
			return dbProps.getProperty(key);
		}
		}

