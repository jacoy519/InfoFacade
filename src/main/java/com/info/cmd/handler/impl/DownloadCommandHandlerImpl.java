package com.info.cmd.handler.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.info.entity.CommandTaskResponseEntity;


@Component
public class DownloadCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	
	@Override
	protected CommandTaskResponseEntity exec(String cmd, List<String> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		// TODO Auto-generated method stub
		return false;
	}


}
