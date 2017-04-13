package com.info.http.factory;

import com.info.http.content.AbstractHttpMethodContent;
import com.info.http.content.HttpGetContent;
import com.info.http.content.HttpPostContent;
import com.info.http.enums.HttpMethodType;
import com.info.http.model.HttpRequestModel;

public class HttpMethodContentFactory {
	
	public static AbstractHttpMethodContent getHttpMethodContent(HttpRequestModel httpRequestModel, HttpMethodType httpMethodType) {
		AbstractHttpMethodContent httpMethodContent = null;
		switch(httpMethodType) {
		case GET:
			httpMethodContent = new HttpGetContent(httpRequestModel);
			break;
		case POST:
			httpMethodContent = new HttpPostContent(httpRequestModel);
			break;
		}
		return httpMethodContent;
	}
}
