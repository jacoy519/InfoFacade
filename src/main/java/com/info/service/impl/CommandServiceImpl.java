package com.info.service.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.info.chain.CommandHandlerChain;
import com.info.dao.CommandTaskDao;
import com.info.entity.CommandTaskResponseEntity;
import com.info.entity.NoticeEntity;
import com.info.factory.CommandTaskFactory;
import com.info.factory.NoticeEntityFactory;
import com.info.pojo.CommandTaskDo;
import com.info.service.CommandService;
import com.info.service.NoticeService;


@Service("commandService")
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
			CommandTaskDo commandTask = initNewCommandTask(command);
			launchCommandTask(commandTask);
		} catch (Exception e) {
			handleSubmitCommandTaskFail(e, command);
		}
		return null;
	}
	
	private CommandTaskDo initNewCommandTask(String command) throws Exception {
		CommandTaskDo taskDo = CommandTaskFactory.getCommandTaskDo(command);
		if(!tryAddNewCommandTaskToDB(taskDo)) {
			throw new Exception("fail to add new command task");
		}
		return taskDo;
	}
	
	private void launchCommandTask(final CommandTaskDo commandTask) {
		taskExecutor.execute(new Runnable(){
			public void run() {
				String result = commandHandlerChain.parseCommand(commandTask.getContent());
				handleLaunchCommandTaskSuccess(commandTask, result);
			}});
	}
	
	private void handleSubmitCommandTaskFail(Exception e, String command) {
		logger.error("submit command task fail :" + command + " and error is " + e.getMessage());
		sendCommandSubmitFailNotice(command);
	}
	
	private boolean tryAddNewCommandTaskToDB(CommandTaskDo taskDo) {
		return commandTaskDao.insertNewCommandTask(taskDo) == 1 ? true:false;
	}
	
	
	
	private void sendCommandSubmitFailNotice(String command) {
		NoticeEntity notice = NoticeEntityFactory.getSystemNoticeEntity("命令任务提交失败，请重新提交");
		qqNoticeService.submitNoticeTask(notice);
	}
	
	private void handleLaunchCommandTaskSuccess(CommandTaskDo task, String result) {
		commandTaskDao.updateCommandTaskStatusToSuccess(task);
		NoticeEntity notice = NoticeEntityFactory.getSystemNoticeEntity(result);
		qqNoticeService.submitNoticeTask(notice);
	}
	
	
}
