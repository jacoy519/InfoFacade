package com.info.http.content;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import com.info.http.model.HttpRequestModel;
import com.info.http.model.HttpResponseModel;


public abstract class AbstractHttpMethodContent {
	
	
	
	private HttpRequestModel httpRequestModel;
	
	AbstractHttpMethodContent(HttpRequestModel httpRequestModel) {
		this.httpRequestModel = httpRequestModel;
	}
	
	
	protected abstract URL getUrl(HttpRequestModel httpRequestModel) throws Exception;
	
	protected abstract URLConnection execHttpRequest(URLConnection connection, HttpRequestModel httpRequestModel) throws Exception;
	
	public HttpResponseModel getHttpResponse() throws Exception {
		
        URL url = getUrl(this.httpRequestModel);
       
        URLConnection connection = getConnection(url);
        
        connection = execHttpRequest(connection, this.httpRequestModel);
        
        return getHttpResponseModel(connection);
	}
	
	private URLConnection getConnection(URL url) throws Exception {
		URLConnection connection = null;
		connection = url.openConnection();
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return connection;
	}
	
	private  HttpResponseModel getHttpResponseModel(URLConnection connection) throws Exception {
		HttpResponseModel httpResponseModel = new HttpResponseModel();
		httpResponseModel.setRespnseHeaders(connection.getHeaderFields());
		String responseData = readResponseData(connection);
		httpResponseModel.setResponeseData(responseData);
		return httpResponseModel;
		
	}
	
	private String readResponseData(URLConnection connection) throws IOException {
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try{
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	        String line;
	        while ((line = in.readLine()) != null) {
	            sb.append(line);
	        }
		} finally {
            if (in != null) {
                in.close();
            }
		}
        return sb.toString();
	}
	
}
