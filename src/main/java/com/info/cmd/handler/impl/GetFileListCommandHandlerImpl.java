package com.info.cmd.handler.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.info.util.FileUtils;

@Component("getFileListCommandHandler")
public class GetFileListCommandHandlerImpl extends AbstractCommandHandlerImpl {

	private final static Logger logger = Logger.getLogger(GetFileListCommandHandlerImpl.class);
	
	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		String regEx="获取文件列表";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(cmd);
		return matcher.find();
	}
	
	@Override
	protected String exec(String cmd, List<String> args) throws Exception {
		String fileDirPath = getFileDirPath(args);
		List<String> childfileList = FileUtils.getFileList(fileDirPath);
		return getFileListMessage(fileDirPath, childfileList);
	}
	
	@Override
	protected String getExecFailMessage() {
		return "获取文件列表失败";
	}
	
	private String getFileDirPath(List<String> args) {
		return args.get(0);
	}
	
	private String getFileListMessage(String fileDirPath, List<String> fileList) {
		StringBuffer output = new StringBuffer();
		output.append(fileDirPath + "中的文件:\n");
		for(String childFileName : fileList) {
			output.append(childFileName).append('\n');
		}
		return output.toString();
	}



}
