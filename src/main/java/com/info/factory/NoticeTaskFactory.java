package com.info.factory;

import java.util.UUID;

import com.info.entity.NoticeEntity;
import com.info.pojo.NoticeTaskDo;

public class NoticeTaskFactory {
	
	public static NoticeTaskDo getNoticeTaskEntity(NoticeEntity notice) {
		NoticeTaskDo task = new NoticeTaskDo();
		task.setId(UUID.randomUUID().toString());
		task.setSender(notice.getSender());
		task.setContent(notice.getContent());
		return task;
	}
}
