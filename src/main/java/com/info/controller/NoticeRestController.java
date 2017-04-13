package com.info.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.NoticeEntity;
import com.info.entity.NoticeTaskResponseEntity;
import com.info.service.NoticeService;

@RestController
@RequestMapping(value = "/notice")
public class NoticeRestController {
	
	
	private final static Logger logger = Logger.getLogger(NoticeRestController.class);
	
	@Resource
	private NoticeService qqNoticeService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<NoticeTaskResponseEntity> sendNotice(HttpServletRequest request,@RequestBody NoticeEntity notice) {
		logger.info("receive notice request from "+ request.getRemoteHost());
		NoticeTaskResponseEntity taskResponse = qqNoticeService.submitNoticeTask(notice);
		return new ResponseEntity<NoticeTaskResponseEntity>(taskResponse, HttpStatus.OK);
	}
}
