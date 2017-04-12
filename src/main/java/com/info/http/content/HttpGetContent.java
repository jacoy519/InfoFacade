package com.info.http.content;

import java.net.URL;
import java.net.URLConnection;

import com.info.http.model.HttpRequestModel;


public class HttpGetContent extends AbstractHttpMethodContent {

	public HttpGetContent(HttpRequestModel httpRequestModel) {
		super(httpRequestModel);
	}

	@Override
	protected URL getUrl(HttpRequestModel httpRequestModel) throws Exception {
		String urlNameString = httpRequestModel.getFullUrl();
        return new URL(urlNameString);
	}

	@Override
	protected URLConnection execHttpRequest(URLConnection connection, HttpRequestModel httpRequestModel) throws Exception{
        connection.connect();
        return connection;
	}

}
