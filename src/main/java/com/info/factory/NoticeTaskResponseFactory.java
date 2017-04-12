package com.info.factory;

import com.info.entity.NoticeTaskResponseEntity;

public class NoticeTaskResponseFactory {
	
	public static NoticeTaskResponseEntity getsubmitTaskFailResponse() {
		return new NoticeTaskResponseEntity("100", "发送消息任务提交失败");
	}
	
	public static NoticeTaskResponseEntity getsubmitTaskSuccessResponse() {
		return new NoticeTaskResponseEntity("200", "发送消息任务提交成功");
	}
	
}
