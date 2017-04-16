package com.info.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.pojo.QQCommandDo;
import com.info.pojo.QQCommandReplay;

@RestController
@RequestMapping(value="/command")
public class CommandRestController {
	
	private static Logger logger = Logger.getLogger(CommandRestController.class);
	
	
	@RequestMapping(value="/QQ", method = RequestMethod.POST, produces = "application/json; charset=utf-8")	
	public ResponseEntity<QQCommandReplay> receiveCommandFromQQ(@RequestBody QQCommandDo command) {
		
		logger.info("receive message from QQ " + command.getSender_uid());
		if(command.getContent() == null) {
			return new ResponseEntity<QQCommandReplay>(HttpStatus.NON_AUTHORITATIVE_INFORMATION); 
		}
		QQCommandReplay replay = new QQCommandReplay();
		replay.setReplay("收到消息");
		replay.setCode(0);
		return new ResponseEntity<QQCommandReplay>(replay, HttpStatus.OK); 
	}
}
