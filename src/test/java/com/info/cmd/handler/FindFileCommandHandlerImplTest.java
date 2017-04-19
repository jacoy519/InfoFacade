package com.info.cmd.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.info.test.base.JunitTestBaseTest;

public class FindFileCommandHandlerImplTest extends JunitTestBaseTest{
	
	private final static Logger logger = Logger.getLogger(FindFileCommandHandlerImplTest.class);
	
	@Resource
	private CommandHandler findFileCommandHandler;
	
	@Test
	public void test() {
		String cmd = "查找文件";
		List<String> args = new ArrayList<String>();
		args.add("D:/");
		args.add("baidu");
		String message = findFileCommandHandler.runCommand(cmd, args);
		logger.info(message);
	}
}
