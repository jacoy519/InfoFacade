package com.info.cmd.handler.impl;

import com.info.cmd.handler.CommandHandler;
import com.info.entity.CommandTaskResponseEntity;

public abstract class AbstractCommandHandlerImpl implements CommandHandler {
	
	private CommandHandler nextHandler;
	
	public CommandTaskResponseEntity runCommand(String cmd, String args) {
		
		if(isMatchHandlerRule(cmd)) {
			return exec(cmd,args);
		}
		
		if(isExistNextHandler()) {
			return nextHandler.runCommand(cmd, args);
		}

		return null;
	}
	
	protected abstract CommandTaskResponseEntity exec(String cmd, String args);
	
	protected abstract boolean isMatchHandlerRule(String cmd);
	
	private boolean isExistNextHandler() {
		return nextHandler!=null?true:false;
	}
	
	public CommandHandler getNextHandler() {
		return this.nextHandler;
	}
	
	public void setNextHandler(CommandHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
}
