package com.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= "C://Program Files//Git//testDemo//src//main//resources//Config.properties";

	
	public ConfigFileReader() {
		
		BufferedReader reader;
		
		try {
			
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			
			try {
				properties.load(reader);
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Config.properties not found at " + propertyFilePath);
			
		}		
	}
	
	public String getDriverPath(){
		
		String driverPath = properties.getProperty("driverPath");
		
		if(driverPath!= null) {
			return driverPath;
		}
		else {
			throw new RuntimeException("driverPath not specified in the Config.properties file.");		
		}
		
	}

	
}
