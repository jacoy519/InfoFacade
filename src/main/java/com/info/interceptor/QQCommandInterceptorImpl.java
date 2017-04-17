package com.info.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class QQCommandInterceptorImpl implements HandlerInterceptor {
	
	private final static Logger logger = Logger.getLogger(QQCommandInterceptorImpl.class);
	
	private Set<String> validSenderUidSet = new HashSet<String>();
	

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		String senderUid = getSenderUidFromRequest(arg0);
		return isSenderUidVaildate(senderUid);
	}
	
	private String getSenderUidFromRequest(HttpServletRequest request) {
		String body = getHttpServletRequestBody(request);
		return getSenderUidFromRequestBody(body);
		
	}
	
	private boolean isSenderUidVaildate(String sendUid) {
		return validSenderUidSet.contains(sendUid);
	}
	
	private String getHttpServletRequestBody(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		String line=null;
		String body = "";
		BufferedReader reader;
		try {
			reader = request.getReader();
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		body = sb.toString();
		return body;
	}
	
	private String getSenderUidFromRequestBody(String body) {
		String regEx = "\"sender_uid\":(.*?),";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(body);
		if(!matcher.find()) {
			return "";
		}
		return matcher.group(1);
	}
	
	public Set<String> getValidSenderUidSet() {
		return this.validSenderUidSet;
	}
	
	public void setValidSenderUidSet(Set<String> validSenderUidSet) {
		this.validSenderUidSet = validSenderUidSet;
	}

}
