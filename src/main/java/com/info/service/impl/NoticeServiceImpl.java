package com.info.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.info.dao.NoticeDao;
import com.info.pojo.NoticeDo;
import com.info.pojo.NoticeResponseDo;
import com.info.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	private static final Logger logger = Logger.getLogger(NoticeServiceImpl.class);
	
	@Resource
	private NoticeDao noticeDao;
	
	public NoticeResponseDo sendNotice(NoticeDo notice) {
		NoticeResponseDo noticeResponseDo = new NoticeResponseDo() ;
		noticeResponseDo.setCode("100");
		noticeResponseDo.setStatus("测试");
		return noticeResponseDo;
	}

}
