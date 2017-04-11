package com.info.service;

import com.info.pojo.NoticeDo;
import com.info.pojo.NoticeResponseDo;

public interface NoticeService {
	
	NoticeResponseDo sendNotice(NoticeDo notice);
}
