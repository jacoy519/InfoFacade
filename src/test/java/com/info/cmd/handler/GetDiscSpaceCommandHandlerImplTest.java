package com.info.cmd.handler;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.info.test.base.JunitTestBaseTest;

public class GetDiscSpaceCommandHandlerImplTest extends JunitTestBaseTest{
	
	private final static Logger logger = Logger.getLogger(GetDiscSpaceCommandHandlerImplTest.class);
	
	@Resource
	private CommandHandler getDiscSpaceCommandHandler;
	
	@Test
	public void testSuccessCommand() {
		String cmd = "获取磁盘容量";
		String message = getDiscSpaceCommandHandler.runCommand(cmd, new ArrayList<String>());
		logger.info(message);
		
	}
	
	@Test
	public void testFailCommand() {
		String cmd = "123123123123";
		String message = getDiscSpaceCommandHandler.runCommand(cmd, new ArrayList<String>());
		logger.info(message);
		
	}
}
