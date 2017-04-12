package com.info.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.info.dao.NoticeDao;
import com.info.pojo.NoticeDo;
import com.info.pojo.NoticeResponseDo;
import com.info.service.NoticeService;

public abstract class AbstractNoticeServiceImpl implements NoticeService{
	
	private final static Logger logger = Logger.getLogger(NoticeService.class);
	
	@Resource
	private NoticeDao noticeDao;
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	protected abstract NoticeResponseDo sendNotice(NoticeDo notice) throws Exception;

	public NoticeResponseDo submitSendNoticeTask(final NoticeDo notice) {
		// TODO Auto-generated method stub
		NoticeResponseDo response = new NoticeResponseDo();
		
		//首先入库
		int newSerialNumber=addNoticeSerial(notice);
		if(newSerialNumber == 0) {
			response.setCode("200");
			response.setStatus("提交发送请求失败，请重新提交");
			return response;
		}
		
		//提交任务给线程池
		taskExecutor.execute(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				try {
					sendNotice(notice);
				} catch (Exception e) {
					setFailNoticeSerial();
				}
				
			}});
		
		//设置返回结果
		response.setCode("100");
		response.setStatus("提交发送请求成功");
		return response;
	}
	
	private int addNoticeSerial(NoticeDo notice) {
		return noticeDao.insertNotice(notice);
	}
	
	private void setFailNoticeTask() {
		
	}
	
	
	private void setSuccessNoticeTask() {
		
	}
	
	
	
}
