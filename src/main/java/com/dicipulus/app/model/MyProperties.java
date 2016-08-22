package com.dicipulus.app.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;


public final class MyProperties {	
	private static final Logger logger =LoggerFactory.getLogger(MyProperties.class);
	private static String rootPath=null;
	private static String host=null;
	
	
	public final static String getRootPath(){
		Properties  prop = new Properties();
		InputStream input= null;
		
		try{
			//input = new FileInputStream("resources/config.properties");
			input =new ClassPathResource("config.properties").getInputStream();
			prop.load(input);
			rootPath=prop.getProperty("rootPath");
			logger.info(rootPath);
		}catch (IOException ex) {
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
		return rootPath;
	}
	
	public final static String getHost(){
		Properties prop =new Properties();
		InputStream input=null;
		try{
			input=new ClassPathResource("config.properties").getInputStream();
			prop.load(input);
			host=prop.getProperty("host");
			logger.info(host);
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {
			if (input!=null){
				try{
					input.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return host;
	}
	
}
