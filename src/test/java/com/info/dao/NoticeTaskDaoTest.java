package com.info.dao;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.info.pojo.NoticeTaskDo;
import com.info.test.base.JunitTestBaseTest;

public class NoticeTaskDaoTest extends JunitTestBaseTest {
	
	@Resource
	private NoticeTaskDao noticeTaskDao;
	
	@Test
	public void test(){
		for(int i=0;i<10;i++) {
			NoticeTaskDo noticeTask = new NoticeTaskDo();
			noticeTask.setNoticeTaskId(UUID.randomUUID().toString());
			noticeTask.setNoticeSender("test"+i);
			noticeTask.setNoticeMessage("message"+i);
			System.out.println("insert "+ noticeTaskDao.insertNewNoticeTask(noticeTask) + " notice");
		}
	}
}
