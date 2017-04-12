package com.info.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.info.http.model.HttpRequestModel;
import com.info.http.model.HttpResponseModel;
import com.info.http.util.HttpRequestUtils;


@Service("qqMessageHandler")
public class QQMessageHandler implements MessageHandler {
	
	private final static Logger logger = Logger.getLogger(QQMessageHandler.class);

	public String receiveMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendMessage(String message) throws Exception {
		logger.info("request");
		HttpRequestModel messageSendRequest = new HttpRequestModel();
		messageSendRequest.setUrl("http://127.0.0.1:5000/openqq/send_friend_message");
		messageSendRequest.addParam("uid", "824244047");
		messageSendRequest.addParam("content", message);
		HttpResponseModel outputResult = HttpRequestUtils.execPostHttpRequest(messageSendRequest);
	}

}
