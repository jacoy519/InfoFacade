package com.info.cmd.handler.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.info.entity.CommandTaskResponseEntity;


@Component
public class DownloadCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	private final static Logger logger = Logger.getLogger(DownloadCommandHandlerImpl.class);
	
	
	@Override
	protected CommandTaskResponseEntity exec(String cmd, String args) {
		// TODO Auto-generated method stub
		logger.info("exec command");
		return null;
	}

	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		// TODO Auto-generated method stub
		logger.info("try match command");
		return true;
	}

}
