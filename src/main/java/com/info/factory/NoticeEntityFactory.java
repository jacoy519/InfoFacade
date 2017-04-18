package com.info.factory;

import com.info.entity.NoticeEntity;

public class NoticeEntityFactory {
	
	private final static String SYS_SENDER = "Info facade";
	
	private final static String PARSE_CMD_FAIL_MSG = "解析命令失败";
	
	public static NoticeEntity getSystemNoticeEntity(String content) {
		return getNoticeEntity(SYS_SENDER, content);
	}
	
	public static NoticeEntity getParseCommandFailNoticeEntity() {
		return getNoticeEntity(SYS_SENDER, PARSE_CMD_FAIL_MSG);
	}
	
	private static NoticeEntity getNoticeEntity(String sender, String content) {
		NoticeEntity notice = new NoticeEntity();
		notice.setSender(sender);
		notice.setContent(content);
		return notice;
	}
}
