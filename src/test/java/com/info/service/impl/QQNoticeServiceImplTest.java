package com.info.service.impl;


import javax.annotation.Resource;

import org.junit.Test;

import com.info.entity.NoticeEntity;
import com.info.entity.NoticeTaskResponseEntity;
import com.info.service.NoticeService;
import com.info.test.base.JunitTestBaseTest;

public class QQNoticeServiceImplTest extends JunitTestBaseTest {
	
	@Resource
	private NoticeService qqNoticeService;
	
	@Test
	public void test(){
			NoticeEntity notice=new NoticeEntity();
			notice.setSender("test");
			notice.setContent("message");
			NoticeTaskResponseEntity response = qqNoticeService.submitNoticeTask(notice);
			System.out.println("status: "+response.getStatus());
	}
}
