package com.info.cmd.handler;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.info.test.base.JunitTestBaseTest;

public class GetIPCommandHandlerImplTest extends JunitTestBaseTest{
	
	private final static Logger logger = Logger.getLogger(GetIPCommandHandlerImplTest.class);
	
	@Resource
	private CommandHandler getIPCommandHandler;
	
	@Test
	public void testSuccessCommand() {
		String cmd = "获取IP";
		String message = getIPCommandHandler.runCommand(cmd, null);
		logger.info(message);
	}
}
