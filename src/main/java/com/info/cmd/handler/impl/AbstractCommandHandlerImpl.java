package com.info.cmd.handler.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.info.cmd.handler.CommandHandler;
import com.info.entity.CommandTaskResponseEntity;

public abstract class AbstractCommandHandlerImpl implements CommandHandler {
	
	private final static Logger logger = Logger.getLogger(CommandHandler.class);
	
	private CommandHandler nextHandler;
	
	public CommandTaskResponseEntity runCommand(String cmd, List<String> args) {
		
		if(isMatchHandlerRule(cmd)) {
			logger.info(cmd + " is matched by "+ getCommandHandlerName());
			return exec(cmd,args);
		}
		
		if(isExistNextHandler()) {
			return nextHandler.runCommand(cmd, args);
		}

		return null;
	}
	
	protected abstract CommandTaskResponseEntity exec(String cmd, List<String> args);
	
	protected abstract boolean isMatchHandlerRule(String cmd);
	
	protected String getCommandHandlerName() {
		return this.getClass().getSimpleName();
	}
	
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
