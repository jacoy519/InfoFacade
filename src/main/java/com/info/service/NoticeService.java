package com.info.service;

import com.info.entity.NoticeEntity;
import com.info.entity.NoticeTaskResponseEntity;

public interface NoticeService {
	
	NoticeTaskResponseEntity submitNoticeTask(NoticeEntity notice);
}
