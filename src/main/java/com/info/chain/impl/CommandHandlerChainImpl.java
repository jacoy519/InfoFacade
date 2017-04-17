package com.info.chain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.info.chain.CommandHandlerChain;
import com.info.cmd.handler.*;
import com.info.cmd.handler.impl.AbstractCommandHandlerImpl;

@Component("commandHandlerChain")
public class CommandHandlerChainImpl implements CommandHandlerChain {
	
	private final static Logger logger = Logger.getLogger(CommandHandlerChainImpl.class);
	
	@Autowired
	private Set<AbstractCommandHandlerImpl> CommandHandlerSet;
	
	private CommandHandler beginHandler;
	
	@PostConstruct
	private void initCommandHandlerChain() {
		AbstractCommandHandlerImpl current = null;
		for(AbstractCommandHandlerImpl handler : CommandHandlerSet) {
			logger.info("set Chain");
			if(beginHandler == null) {
				beginHandler = handler;
			}
			
			if(current == null) {
				current = handler;
			} else {
				current.setNextHandler(handler);
				current = handler;
			}
		}
	}
	
	public void parseCommand(String command) {
		String[] commandSplit = command.split(" ");
		if(commandSplit.length == 0 ) {
			return;
		}
		String cmd = commandSplit[0];
		List<String> args = new ArrayList<String>();
		for(int i=1;i<commandSplit.length;i++) {
			args.add(commandSplit[i]);
		}
		beginHandler.runCommand(cmd, args);
	}

}
