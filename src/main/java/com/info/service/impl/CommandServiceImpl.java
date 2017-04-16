package com.info.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.info.chain.CommandHandlerChain;
import com.info.cmd.handler.CommandHandler;
import com.info.dao.CommandTaskDao;
import com.info.entity.CommandEntity;
import com.info.entity.CommandTaskResponseEntity;
import com.info.entity.NoticeEntity;
import com.info.factory.CommandTaskFactory;
import com.info.pojo.CommandTaskDo;
import com.info.service.CommandService;
import com.info.service.NoticeService;

public class CommandServiceImpl implements CommandService {
	
	private final static Logger logger = Logger.getLogger(CommandServiceImpl.class);
	
	@Resource
	private CommandTaskDao commandTaskDao;
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Resource 
	private NoticeService qqNoticeService;
	
	@Resource
	private CommandHandlerChain commandHandlerChain;
	
	
	public CommandTaskResponseEntity submitCommandTask(String command) {
		try {
			initNewCommandTask(command);
			launchCommandTask(command);
		} catch (Exception e) {
			handleSubmitCommandTaskFail(e, command);
		}
		sendCommandSubmitSuccessNotice();
		return null;
	}
	
	private void initNewCommandTask(String command) throws Exception {
		CommandTaskDo taskDo = CommandTaskFactory.getCommandTaskDo(command);
		if(!tryAddNewCommandTaskToDB(taskDo)) {
			throw new Exception("fail to add new command task");
		}
	}
	
	private void launchCommandTask(final String command) {
		taskExecutor.execute(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				commandHandlerChain.paresCommandHandlerChain(command);
			}});
	}
	
	private void handleSubmitCommandTaskFail(Exception e, String command) {
		logger.error("submit command task fail :" + command + " and error is " + e.getMessage());
		NoticeEntity notice = new NoticeEntity();
		notice.setSender("Info Facade System");
		notice.setContent("命令任务提交失败，任务为：" + command);
		qqNoticeService.submitNoticeTask(notice);
	}
	
	
	private void sendCommandSubmitSuccessNotice() {
		NoticeEntity notice = new NoticeEntity();
		notice.setSender("Info Facade System");
		notice.setContent("命令任务提交成功");
		qqNoticeService.submitNoticeTask(notice);
	}
	
	private boolean tryAddNewCommandTaskToDB(CommandTaskDo taskDo) {
		return commandTaskDao.insertNewCommandTask(taskDo) == 1 ? true:false;
	}

}
