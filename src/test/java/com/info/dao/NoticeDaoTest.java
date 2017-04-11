package com.info.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.info.pojo.NoticeDo;
import com.info.test.base.JunitTestBaseTest;

public class NoticeDaoTest extends JunitTestBaseTest {
	
	@Resource
	private NoticeDao noticeDao;
	
	@Test
	public void test(){
		for(int i=0;i<10;i++) {
			NoticeDo notice=new NoticeDo();
			notice.setNoticeSender("test"+i);
			notice.setNoticeMessage("message"+i);
			System.out.println("insert "+ noticeDao.insertNotice(notice) + " notice");
		}
	}
}
