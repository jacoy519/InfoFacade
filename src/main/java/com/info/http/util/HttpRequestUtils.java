package com.info.http.util;

import com.info.http.content.AbstractHttpMethodContent;
import com.info.http.enums.HttpMethodType;
import com.info.http.factory.HttpMethodContentFactory;
import com.info.http.model.HttpRequestModel;
import com.info.http.model.HttpResponseModel;

public class HttpRequestUtils {
	
	public static HttpResponseModel execGetHttpRequest(HttpRequestModel httpRequestModel) throws Exception {
        return execHttpRequest(httpRequestModel, HttpMethodType.GET);
	}
	
	
    public static HttpResponseModel execPostHttpRequest(HttpRequestModel httpRequestModel) throws Exception {
    	return execHttpRequest(httpRequestModel, HttpMethodType.POST);
    }
    
    public static HttpResponseModel execHttpRequest(HttpRequestModel httpRequestModel, HttpMethodType httpMethodType) throws Exception {
    	System.out.println("HttpRequestUtils");
    	HttpResponseModel httpResponseModel = new HttpResponseModel();
    	AbstractHttpMethodContent httpMethodContent = HttpMethodContentFactory.getHttpMethodContent(httpRequestModel, httpMethodType);
    	httpResponseModel = httpMethodContent.getHttpResponse();
        return httpResponseModel;
    }

    
}
