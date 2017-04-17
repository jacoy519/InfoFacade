package com.info.chain;

import com.info.entity.NoticeEntity;

public interface CommandHandlerChain {
	
	NoticeEntity parseCommand(String command);
}
