package com.info.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.CommandTaskResponseEntity;
import com.info.entity.QQCommandEntity;
import com.info.service.CommandService;

@RestController
@RequestMapping(value="/command")
public class CommandRestController {
	
	private static Logger logger = Logger.getLogger(CommandRestController.class);
	
	@Resource
	private CommandService commandService;
	
	
	@RequestMapping(value="/QQ", method = RequestMethod.POST, produces = "application/json; charset=utf-8")	
	public ResponseEntity<CommandTaskResponseEntity> receiveCommandFromQQ(@RequestBody QQCommandEntity command) {
		
		logger.info("receive command from QQ " + command.getSender_uid());
		CommandTaskResponseEntity response = commandService.submitCommandTask(command.getContent());
		return new ResponseEntity<CommandTaskResponseEntity>(response, HttpStatus.OK); 
	}
}
