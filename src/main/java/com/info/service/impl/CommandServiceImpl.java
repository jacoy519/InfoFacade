package com.info.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.info.chain.CommandHandlerChain;
import com.info.dao.CommandTaskDao;
import com.info.entity.CommandTaskResponseEntity;
import com.info.entity.NoticeEntity;
import com.info.factory.CommandTaskFactory;
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
			initNewCommandTask(command);
			launchCommandTask(command);
		} catch (Exception e) {
			handleSubmitCommandTaskFail(e, command);
		}
		handleSubmitCommandTaskSuccess(command);
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
				commandHandlerChain.parseCommand(command);
			}});
	}
	
	private void handleSubmitCommandTaskSuccess(String command) {
		logger.error("submit command task success :" + command);
		sendCommandSubmitSuccessNotice(command);
	}
	
	private void handleSubmitCommandTaskFail(Exception e, String command) {
		logger.error("submit command task fail :" + command + " and error is " + e.getMessage());
		sendCommandSubmitFailNotice(command);
	}
	
	private boolean tryAddNewCommandTaskToDB(CommandTaskDo taskDo) {
		return commandTaskDao.insertNewCommandTask(taskDo) == 1 ? true:false;
	}
	
	
	private void sendCommandSubmitSuccessNotice(String command) {
		NoticeEntity notice = new NoticeEntity();
		notice.setSender("Info Facade System");
		notice.setContent("命令任务提交成功，命令为" + command);
		qqNoticeService.submitNoticeTask(notice);
	}
	
	private void sendCommandSubmitFailNotice(String command) {
		NoticeEntity notice = new NoticeEntity();
		notice.setSender("Info Facade System");
		notice.setContent("命令任务提交失败，任务为：" + command);
		qqNoticeService.submitNoticeTask(notice);
	}
	


}
