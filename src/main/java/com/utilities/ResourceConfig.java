package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

@SuppressWarnings("unused")
public class ResourceConfig {

/*	private String ndtvUrl;
	private String weatherApiUrl;*/
	public static Map<String, String> propMap = null;
	
	public static void loadProperties() {
		
		try {
		String dirPath = System.getProperty("user.dir");
		String configpath = dirPath+File.separator+"config";
		String configFile = configpath+File.separator+"Resources.properties";	
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(configFile);
		prop.load(fis);
		propMap = new HashMap<String, String>();
		for(String name : prop.stringPropertyNames()) {
/*			
			System.out.println(name+".."+prop.getProperty(name));*/
			propMap.put(name,prop.getProperty(name));
			
		}
		
		}
		
		catch(FileNotFoundException e) {
			
			e.printStackTrace();
			e.getMessage();
			e.getCause();
		}
		
        catch(IOException e) {
			
			e.printStackTrace();
			e.getMessage();
			e.getCause();
		}
	}
	
	public static Map<String, String> getPropMap(){
		
		return propMap;
	}
	
}
