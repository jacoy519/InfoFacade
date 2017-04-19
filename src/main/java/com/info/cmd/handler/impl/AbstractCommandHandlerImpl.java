package com.info.cmd.handler.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.info.cmd.handler.CommandHandler;
import com.info.content.StandardNoticeContent;

public abstract class AbstractCommandHandlerImpl implements CommandHandler {
	
	private final static Logger logger = Logger.getLogger(CommandHandler.class);
	
	private CommandHandler nextHandler;
	
	public String runCommand(String cmd, List<String> args) {
		
		if(isMatchHandlerRule(cmd)) {
			logger.info(cmd + " is executed by "+ getCommandHandlerName());
			String message = "";
			try {
				message = exec(cmd,args);;
			} catch(Exception e) {
				logger.error(getCommandHandlerName() + "exec the cmd ["+ cmd +"] fail");
				message = getExecFailMessage();
			}
			return message; 
		}
		
		if(isExistNextHandler()) {
			return nextHandler.runCommand(cmd, args);
		}

		logger.error(cmd + " is not matched by any command handler");
		return StandardNoticeContent.NOT_FIND_MATCHED_COMMAND_HANDLER;
	}
	
	protected abstract boolean isMatchHandlerRule(String cmd);
	
	protected abstract String exec(String cmd, List<String> args) throws Exception;
	
	protected abstract String getExecFailMessage();
	
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
