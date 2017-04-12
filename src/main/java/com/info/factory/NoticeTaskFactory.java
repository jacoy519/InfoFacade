package com.info.factory;

import java.util.UUID;

import com.info.entity.NoticeEntity;
import com.info.pojo.NoticeTaskDo;

public class NoticeTaskFactory {
	
	public static NoticeTaskDo getNoticeTaskEntity(NoticeEntity notice) {
		NoticeTaskDo task = new NoticeTaskDo();
		task.setNoticeTaskId(UUID.randomUUID().toString());
		task.setNoticeSender(notice.getSender());
		task.setNoticeMessage(notice.getContent());
		return task;
	}
}
