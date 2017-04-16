package com.info.factory;

import java.util.UUID;

import com.info.pojo.CommandTaskDo;

public class CommandTaskFactory {
	
	public static CommandTaskDo getCommandTaskDo(String command) {
		CommandTaskDo task = new CommandTaskDo();
		task.setId(UUID.randomUUID().toString());
		task.setContent(command);
		return task;
	}
}
