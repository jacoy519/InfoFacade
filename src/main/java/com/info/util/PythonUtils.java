package com.info.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.info.content.PythonFilePathContent;

public class PythonUtils {
	
	private final static Logger logger = Logger.getLogger(PythonUtils.class);
	
	public static Process executePythonFile(String filePath, List<String> args) throws Exception {
		StringBuffer cmdBuffer = new StringBuffer();
		cmdBuffer.append("python \"");
		cmdBuffer.append(PythonFilePathContent.CHECK_IP_PY_FILE);
		cmdBuffer.append("\"");
		for(String arg : args) {
			cmdBuffer.append(" ");
			cmdBuffer.append(arg);
		}
		String systemCmd = cmdBuffer.toString();
		logger.info("execute system command: " + systemCmd);
		return Runtime.getRuntime().exec(systemCmd); 
	}
}
