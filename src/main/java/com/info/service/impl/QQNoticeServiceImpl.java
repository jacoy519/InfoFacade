package com.info.service.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.info.pojo.NoticeDo;
import com.info.pojo.NoticeResponseDo;
import com.info.service.NoticeService;


@Service("qqNoticeService")
public class QQNoticeServiceImpl extends AbstractNoticeServiceImpl{
	
	
	private final static Logger logger = Logger.getLogger(NoticeService.class);
	
	@Override
	protected NoticeResponseDo sendNotice(NoticeDo notice) throws Exception{
		// TODO Auto-generated method stub
		logger.info("send message to QQ: "+ notice.getNoticeMessage());
		return null;
	}
	
}
