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

import com.info.pojo.NoticeDo;
import com.info.pojo.NoticeResponseDo;
import com.info.service.NoticeService;

@RestController
@RequestMapping(value = "/notice")
public class NoticeRestController {
	
	
	private final static Logger logger = Logger.getLogger(NoticeRestController.class);
	
	@Resource
	private NoticeService noticeService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<NoticeResponseDo> sendNotice(HttpServletRequest request,@RequestBody NoticeDo notice) {
		logger.info("receive send notice request from "+ request.getRemoteHost());
		NoticeResponseDo noticeResponse = noticeService.sendNotice(notice);
		return new ResponseEntity<NoticeResponseDo>(noticeResponse, HttpStatus.OK);
	}
}
