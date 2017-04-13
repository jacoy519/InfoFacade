package com.info.http.content;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;


import com.info.http.model.HttpRequestModel;


public class HttpPostContent extends AbstractHttpMethodContent {
	
	
	public HttpPostContent(HttpRequestModel httpRequestModel) {
		super(httpRequestModel);
	}

	@Override
	protected URL getUrl(HttpRequestModel httpRequestModel) throws Exception {
		return new URL(httpRequestModel.getUrl());
	}

	@Override
	protected URLConnection execHttpRequest(URLConnection connection, HttpRequestModel httpRequestModel) throws Exception {
		PrintWriter out = null;
		try {
			connection.setDoOutput(true);
			connection.setDoInput(true);
			out = new PrintWriter(connection.getOutputStream());
			out.print(httpRequestModel.getParamStr());
			out.flush();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
		return connection;
	}
}
