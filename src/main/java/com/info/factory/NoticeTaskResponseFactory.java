package com.info.factory;

import com.info.entity.NoticeTaskResponseEntity;

public class NoticeTaskResponseFactory {
	
	public static NoticeTaskResponseEntity getsubmitTaskFailResponse() {
		return new NoticeTaskResponseEntity("100", "submit notice task fail");
	}
	
	public static NoticeTaskResponseEntity getsubmitTaskSuccessResponse() {
		return new NoticeTaskResponseEntity("200", "submit notice task success");
	}
	
}
