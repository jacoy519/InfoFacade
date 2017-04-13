package com.info.service.impl;


import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.info.entity.NoticeEntity;
import com.info.entity.NoticeTaskResponseEntity;
import com.info.factory.NoticeTaskResponseFactory;
import com.info.service.NoticeService;
import com.info.test.base.JunitTestBaseTest;

public class QQNoticeServiceImplTest extends JunitTestBaseTest {
	
	private final static Logger logger = Logger.getLogger(QQNoticeServiceImplTest.class);
	
	@Resource
	private NoticeService qqNoticeService;
	
	@Test
	public void testSubmitTaskSuccess() throws InterruptedException{
		
			NoticeTaskResponseEntity expectResponse = NoticeTaskResponseFactory.getsubmitTaskSuccessResponse();
		
			NoticeEntity notice=new NoticeEntity();
			notice.setSender("test");
			notice.setContent("message");
			NoticeTaskResponseEntity response = qqNoticeService.submitNoticeTask(notice);
			System.out.println(response.getStatus());
			assertEquals(expectResponse, response);
			
			Thread.sleep(30000);
			
	}
	
	@Test
	public void testFailSubmitNoticeTask() {
		
			NoticeTaskResponseEntity expectResponse = NoticeTaskResponseFactory.getsubmitTaskFailResponse();
			
			NoticeEntity notice=new NoticeEntity();
			notice.setContent("message");
			NoticeTaskResponseEntity response = qqNoticeService.submitNoticeTask(notice);
			
			assertEquals(expectResponse, response);
	}
}
