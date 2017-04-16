package com.info.cmd.handler.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.info.entity.CommandTaskResponseEntity;

@Component
public class CheckCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	private final static Logger logger = Logger.getLogger(CheckCommandHandlerImpl.class);
	@Override
	protected CommandTaskResponseEntity exec(String cmd, String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		// TODO Auto-generated method stub
		logger.info("try match handler");
		return false;
	}

}
