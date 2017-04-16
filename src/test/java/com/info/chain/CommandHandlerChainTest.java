package com.info.chain;

import javax.annotation.Resource;

import org.junit.Test;

import com.info.entity.CommandEntity;
import com.info.test.base.JunitTestBaseTest;

public class CommandHandlerChainTest extends JunitTestBaseTest {
	
	@Resource
	private CommandHandlerChain commandHandlerChain;
	
	@Test
	public void test(){
		commandHandlerChain.paresCommandHandlerChain("test");
	}
}
