package com.info.http.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequestModel {
	
	private String url;
	
	private Map<String,String> params = new HashMap<String,String>();
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
	
	public void deleteParam(String key) {
		params.remove(key);
	}
	
	public String getFullUrl() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(url);
		sb.append('?');
		sb.append(getParamStr());
		return sb.toString();
	}
	
	public String getParamStr() {
		StringBuffer sb = new StringBuffer();
		for(Entry<String,String> param : params.entrySet()) {
			sb.append(param.getKey());
			sb.append('=');
			sb.append(param.getValue());
			sb.append('&');
		}
		return sb.toString();
	}
	
	public void setParams(Map<String,String> params) {
		this.params = params;
	}
	
	public Map<String,String> getParams() {
		return this.params;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
}
