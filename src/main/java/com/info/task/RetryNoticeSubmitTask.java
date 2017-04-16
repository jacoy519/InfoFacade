package com.info.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.info.dao.NoticeTaskDao;
import com.info.pojo.NoticeTaskDo;
import com.info.service.NoticeService;

@Component
public class RetryNoticeSubmitTask {

	private final static Logger logger = Logger.getLogger(RetryNoticeSubmitTask.class);
	
	@Resource
	private NoticeService qqNoticeService;
	
	@Resource
	private NoticeTaskDao noticeTaskDao;
	
    @Scheduled(cron="0 0/1 *  * * ? ")   //每5秒执行一次      
    public void retryNotice() {
    	List<NoticeTaskDo> retryNoticeTaskList = noticeTaskDao.selectNeedRetryTaskList();
    	logger.info(retryNoticeTaskList.size() + " notice task need to retry");
    	for(NoticeTaskDo retryNoticeTask : retryNoticeTaskList) {
    		qqNoticeService.submitRetryNoticeTask(retryNoticeTask);
    	}
    }      
}
