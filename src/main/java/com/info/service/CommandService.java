package com.info.service;

import com.info.entity.CommandEntity;
import com.info.entity.CommandTaskResponseEntity;

public interface CommandService {
	
	
	CommandTaskResponseEntity submitCommandTask(String command);
}
