package com.info.cmd.handler.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.info.util.FileUtils;

@Component("findFileCommandHandler")
public class FindFileCommandHandlerImpl extends AbstractCommandHandlerImpl {
	


	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		String regEx="查找文件";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(cmd);
		return matcher.find();
	}
	
	
	@Override
	protected String exec(String cmd, List<String> args)  throws Exception{
		String fileRootPath = getSearchFileRootPath(args);
		String targetFile = getSearchTargetFileName(args);
		List<String> searchFilePathList = FileUtils.findFile(targetFile, fileRootPath);
		return searchFilePathListToString(searchFilePathList);
	}
	
	@Override
	protected String getExecFailMessage() {
		// TODO Auto-generated method stub
		return "查找文件失败";
	}
	
	private String getSearchFileRootPath(List<String> args) {
		return args.get(0);
	}
	
	private String getSearchTargetFileName(List<String> args) {
		return args.get(1);
	}
	
	private String searchFilePathListToString(List<String> searchFileList) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("搜索结果:\n");
		for(String searchFilePath : searchFileList) {
			strBuilder.append(searchFilePath);
			strBuilder.append('\n');
		}
		return strBuilder.toString();
	}


}
