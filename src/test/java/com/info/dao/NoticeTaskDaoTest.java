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
			NoticeTaskDo noticeTask = new NoticeTaskDo();
			noticeTask.setId(UUID.randomUUID().toString());
			noticeTask.setSender("test");
			noticeTask.setContent("message");
			System.out.println("insert "+ noticeTaskDao.insertNewNoticeTask(noticeTask) + " notice");

			System.out.println("update "+ noticeTaskDao.updateNoticeTaskStatusToAttempt(noticeTask) + " notice");
	}
}
