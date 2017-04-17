package com.info.cmd.handler;

import java.util.List;

import com.info.entity.CommandTaskResponseEntity;

public interface CommandHandler {
	
	CommandTaskResponseEntity runCommand(String cmd, List<String> args);
}
