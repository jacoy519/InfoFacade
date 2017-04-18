package com.info.cmd.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.info.test.base.JunitTestBaseTest;

public class GetFileListCommandHandlerImplTest  extends JunitTestBaseTest{
	
	private final static Logger logger = Logger.getLogger(GetFileListCommandHandlerImplTest.class);
	
	@Resource
	private CommandHandler getFileListCommandHandler;
	
	@Test
	public void testSuccessCommand() {
		String cmd = "获取文件列表";
		List<String> args = new ArrayList<String>();
		args.add("D:/");
		String message = getFileListCommandHandler.runCommand(cmd, args);
		logger.info(message);
	}
	
	@Test
	public void testFailCommand() {
		
	}
}
