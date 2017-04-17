package com.info.cmd.handler;

import java.util.List;

import com.info.entity.CommandTaskResponseEntity;
import com.info.entity.NoticeEntity;

public interface CommandHandler {
	
	NoticeEntity runCommand(String cmd, List<String> args);
}
