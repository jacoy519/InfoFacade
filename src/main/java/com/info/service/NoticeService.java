package com.info.service;

import com.info.entity.NoticeEntity;
import com.info.entity.NoticeTaskResponseEntity;
import com.info.pojo.NoticeTaskDo;

public interface NoticeService {
	
	NoticeTaskResponseEntity submitNoticeTask(NoticeEntity notice);
	
	NoticeTaskResponseEntity submitRetryNoticeTask(NoticeTaskDo noticeTask);
}
