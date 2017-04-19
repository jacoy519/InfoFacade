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
	
	private final static int MAX_MSE_LENGTH = 888;
	
	private final static String CHANGE_LINE_TAG = "%0a";

	@Override
	protected void execNoticeTask(NoticeTaskDo noticeTask) throws Exception {
		String message = noticeTask.getContent();
		List<String> splitedMessages = splitMessageAndReplaceChangeLineTag(message);
		for(String splitedMessage : splitedMessages) {
			sendMessage(splitedMessage);
		}
	}
	
	private List<String> splitMessageAndReplaceChangeLineTag(String message) {
		String[] splitMessages = message.split("\n");
		List<String> messages = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i< splitMessages.length ; i++) {
			String splitMessage = splitMessages[i];
			int beforeLength = sb.length();
			if((beforeLength + splitMessage.length()) < MAX_MSE_LENGTH) {
				sb.append(splitMessage).append(CHANGE_LINE_TAG);
				continue;
			} else {
				messages.add(sb.toString());
				sb = new StringBuffer();
				if(splitMessage.length() < MAX_MSE_LENGTH) {
					sb.append(splitMessage).append(CHANGE_LINE_TAG);
					continue;
				}
				int start = 0;
				int end = MAX_MSE_LENGTH;
				while(start<splitMessage.length()) {
					messages.add(splitMessage.substring(start, end));
					start = end;
					end = end + MAX_MSE_LENGTH;
					end = (end>=splitMessage.length())?splitMessage.length():end;
				}
			}
		}
		if(sb.length()>0) {
			messages.add(sb.toString());
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
