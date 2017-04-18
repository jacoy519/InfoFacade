package com.info.cmd.handler;

import java.util.List;


public interface CommandHandler {
	
	String runCommand(String cmd, List<String> args);
}
