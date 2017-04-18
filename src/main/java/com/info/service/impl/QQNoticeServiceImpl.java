package com.info.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.info.http.model.HttpRequestModel;
import com.info.http.model.HttpResponseModel;
import com.info.http.util.HttpRequestUtils;
import com.info.pojo.NoticeTaskDo;
import com.info.service.NoticeService;


@Service("qqNoticeService")
public class QQNoticeServiceImpl extends AbstractNoticeServiceImpl{
	
	
	private final static Logger logger = Logger.getLogger(NoticeService.class);
	
	private final static int MAX_MSE_LENGTH = 900;
	
	private final static String ChANG_LINE_TAG = "%0a";

	@Override
	protected void execNoticeTask(NoticeTaskDo noticeTask) throws Exception {
		String message = noticeTask.getContent();
		message = message.replace("\n", "%0a");
		List<String> splitedMessages = splitMessage(message);
		for(String splitedMessage : splitedMessages) {
			sendMessage(splitedMessage);
		}
	}
	
	private List<String> splitMessage(String message) {
		List<String> messages = new ArrayList<String>();
		int start = 0 ;
		int end = MAX_MSE_LENGTH>=message.length()?message.length():MAX_MSE_LENGTH; 
		while(start<message.length()) {
			messages.add(message.substring(start, end));
			start = end;
			end = end + MAX_MSE_LENGTH;
			end = end>=message.length()?message.length():end;
		}
		logger.info("split message number :" + messages.size());
		return messages;
	}
	
	private void sendMessage(String message) throws Exception {
		logger.info("send message:"+ message);
		HttpRequestModel messageSendRequest = new HttpRequestModel();
		messageSendRequest.setUrl("http://127.0.0.1:5000/openqq/send_friend_message");
		messageSendRequest.addParam("uid", "824244047");
		messageSendRequest.addParam("content", message);
		HttpResponseModel response = HttpRequestUtils.execPostHttpRequest(messageSendRequest);
		logger.info("receive response: "+ response.getResponseData());
	}
	
}
