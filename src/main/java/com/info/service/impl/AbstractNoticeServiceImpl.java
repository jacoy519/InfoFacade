package com.info.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.info.dao.NoticeTaskDao;
import com.info.entity.NoticeEntity;
import com.info.entity.NoticeTaskResponseEntity;
import com.info.factory.NoticeTaskFactory;
import com.info.factory.NoticeTaskResponseFactory;
import com.info.pojo.NoticeTaskDo;
import com.info.service.NoticeService;

public abstract class AbstractNoticeServiceImpl implements NoticeService{
	
	private final static Logger logger = Logger.getLogger(NoticeService.class);
	
	@Resource
	private NoticeTaskDao noticeTaskDao;
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	protected abstract void execNoticeTask(NoticeTaskDo noticeTask) throws Exception;

	public NoticeTaskResponseEntity submitNoticeTask(final NoticeEntity notice) {

		NoticeTaskResponseEntity response = null;
		try {
			NoticeTaskDo noticeTask = initNoticeTask(notice);
			response = launchNoticeTask(noticeTask);
		} catch (Exception e) {
			response = handleSubmitNoticeTaskException(e);
		}
		return response;
		
	}
	
	
	public NoticeTaskResponseEntity submitRetryNoticeTask(NoticeTaskDo noticeTask) {
		NoticeTaskResponseEntity response = null;
		try {
			resetNoticeTaskStatus(noticeTask);
			launchNoticeTask(noticeTask);
		} catch (Exception e) {
			response = handleSubmitRetryNoticeTaskException(e, noticeTask);
		}
		return response;
	}
	
	private NoticeTaskResponseEntity handleSubmitNoticeTaskException(Exception e) {
		logger.error("fail to sumbit send notice task, the error is "+ e.getMessage());
		return NoticeTaskResponseFactory.getsubmitTaskFailResponse();
	}
	
	private NoticeTaskResponseEntity handleSubmitRetryNoticeTaskException(Exception e, NoticeTaskDo noticeTask) {
		logger.error("fail to sumbit retry send notice task, the error is "+ e.getMessage());
		return NoticeTaskResponseFactory.getsubmitTaskFailResponse();
	}
	
	private NoticeTaskDo initNoticeTask(NoticeEntity notice) throws Exception{
		NoticeTaskDo task = NoticeTaskFactory.getNoticeTaskEntity(notice);
		if (!tryAddNewNoticeTaskToDB(task)) {
			throw new Exception("fail add notice task to DB");
		}
		return task;
	}
	
	private void resetNoticeTaskStatus(NoticeTaskDo task) throws Exception {
		if(!tryUpdateRetryNoticeTaskStatusToAttempt(task)) {
			throw new Exception("fail reset notice task status to attempt and the task id is "+ task.getId());
		}
	}
	
	private boolean tryUpdateRetryNoticeTaskStatusToAttempt(NoticeTaskDo task) {
		return noticeTaskDao.updateNoticeTaskStatusToAttempt(task) == 1 ? true :false;
	}
	
	private boolean tryAddNewNoticeTaskToDB(NoticeTaskDo task) {
		return noticeTaskDao.insertNewNoticeTask(task) == 1 ? true :false;
	}
	
	private NoticeTaskResponseEntity launchNoticeTask(final NoticeTaskDo noticeTask) {
		taskExecutor.execute(new Runnable(){
			public void run() {
				try {
					execNoticeTask(noticeTask);
					handleExecNoticeTaskSuccess(noticeTask);
				} catch (Exception e) {
					handleExecNoticeTaskException(e, noticeTask);
				}
				
			}});
		return NoticeTaskResponseFactory.getsubmitTaskSuccessResponse();
	}
	
	private void handleExecNoticeTaskException(Exception e, NoticeTaskDo noticeTask) {
		logger.info("fail to launch send notice task, the error is "+ e.getMessage());
		noticeTaskDao.updateNoticeTaskStatusToFailure(noticeTask);
		
	}
	
	private void handleExecNoticeTaskSuccess(NoticeTaskDo noticeTask) {
		logger.info("task exec success");
		noticeTaskDao.updateNoticeTaskStatusToSuccess(noticeTask);
	}
	
}
