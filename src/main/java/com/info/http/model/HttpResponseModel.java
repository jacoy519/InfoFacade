package com.info.http.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponseModel {
	
	private Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
	
	private String responseData;
	
	
	public void setRespnseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	
	public Map<String, List<String>> getRespnseHeaders() {
		return  this.responseHeaders;
	}
	
	public void setResponeseData(String responseData) {
		this.responseData = responseData;
	}
	
	public String getResponseData() {
		return this.responseData;
	}
}
