package com.info.cmd.handler;

import com.info.entity.CommandTaskResponseEntity;

public interface CommandHandler {
	
	CommandTaskResponseEntity runCommand(String cmd, String args);
}
