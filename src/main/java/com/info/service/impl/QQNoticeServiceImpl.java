package com.info.service.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.info.handler.MessageHandler;
import com.info.pojo.NoticeTaskDo;
import com.info.service.NoticeService;


@Service("qqNoticeService")
public class QQNoticeServiceImpl extends AbstractNoticeServiceImpl{
	
	
	private final static Logger logger = Logger.getLogger(NoticeService.class);
	
	@Resource
	private MessageHandler qqMessageHandler; 

	@Override
	protected void execNoticeTask(NoticeTaskDo noticeTask) throws Exception {
		logger.info("exec QQ notice send task and the message is "+ noticeTask.getNoticeMessage());
		qqMessageHandler.sendMessage(noticeTask.getNoticeMessage());
		Thread.sleep(10000);
	}
	
}
