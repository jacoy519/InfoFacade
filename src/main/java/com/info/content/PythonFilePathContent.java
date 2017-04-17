package com.info.content;

import java.io.UnsupportedEncodingException;

public class PythonFilePathContent {
	
	
	private final static String PYTHON_FILE_DIR;
	
	static {
		String configPath =  PythonFilePathContent.class.getResource("/").getPath()+"python/" ;
		try {
			configPath =  java.net.URLDecoder.decode(configPath,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			PYTHON_FILE_DIR = configPath.substring(1, configPath.length());
		} else {
			PYTHON_FILE_DIR = configPath;
		}
		
	}
	
	public final static String CHECK_IP_PY_FILE = PYTHON_FILE_DIR + "checkIP.py";
	
	public static void main(String[] args) {
		System.out.println(CHECK_IP_PY_FILE);
	}
}
