package com.info.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.info.pojo.NoticeDo;
import com.info.pojo.NoticeResponseDo;
import com.info.service.NoticeService;
import com.info.test.base.JunitTestBaseTest;

public class QQNoticeServiceImplTest extends JunitTestBaseTest {
	
	@Resource
	private NoticeService qqNoticeService;
	
	@Test
	public void test(){
		for(int i=0;i<10;i++) {
			NoticeDo notice=new NoticeDo();
			notice.setNoticeSender("test"+i);
			notice.setNoticeMessage("message"+i);
			NoticeResponseDo response = qqNoticeService.submitSendNoticeTask(notice);
			System.out.println("response:" + response.getStatus());
		}
	}
}
