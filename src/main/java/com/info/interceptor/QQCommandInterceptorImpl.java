package com.info.interceptor;

import java.io.BufferedReader;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class QQCommandInterceptorImpl implements HandlerInterceptor {
	
	private final static Logger logger = Logger.getLogger(QQCommandInterceptorImpl.class);
	

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer sb = new StringBuffer();
		String line=null;
		BufferedReader reader = arg0.getReader();
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		String body = sb.toString();
		logger.info(body);

		String regEx = "\"sender_uid\": \"(.*?)\"";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(body);
		
		if(!matcher.find()) {
			return false;
		}
		
		String uid = matcher.group(1);
		logger.info(uid);
		return true;
	}

}
