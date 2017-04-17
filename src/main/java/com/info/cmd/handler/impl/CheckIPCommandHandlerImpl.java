package com.info.cmd.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.info.content.PythonFilePathContent;
import com.info.entity.CommandTaskResponseEntity;
import com.info.entity.NoticeEntity;
import com.info.factory.NoticeEntityFactory;
import com.info.util.PythonUtils;

@Component
public class CheckIPCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	private final static Logger logger = Logger.getLogger(CheckIPCommandHandlerImpl.class);
	
	
	//检查当前运行服务器IP地址
	@Override
	protected NoticeEntity exec(String cmd, List<String> args) {
		try {
			String pythonFilePath = PythonFilePathContent.CHECK_IP_PY_FILE;
			PythonUtils.executePythonFile(pythonFilePath, args);
		} catch (Exception e ) {
			logger.error("error run check ip command exec");
		}
		return NoticeEntityFactory.getParseCommandSuccessNoticeEntity("正在查询当前ip地址");
	}

	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		logger.info("match ");
		List<String> regExList = new ArrayList<String>();
		regExList.add("ip");
		regExList.add("IP");
		regExList.add("Ip");
		for(String regEx : regExList) {
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(cmd);
			if(matcher.find()) {
				return true;
			}
		}
		return false;
	}
	
}
