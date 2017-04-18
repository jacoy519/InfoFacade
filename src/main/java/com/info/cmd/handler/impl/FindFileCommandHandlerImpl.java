package com.info.cmd.handler.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.info.util.FileUtils;

@Component("findFileCommandHandler")
public class FindFileCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	
	@Override
	protected String exec(String cmd, List<String> args) {
		// TODO Auto-generated method stub
		try{
			String fileRootPath = getSearchFileRootPath(args);
			String targetFile = getSearchTargetFileName(args);
			List<String> fileNameList = FileUtils.findFile(targetFile, fileRootPath);
		} catch(Exception e) {
			
		}
		return null;
	}

	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		String regEx="查找文件";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(cmd);
		return matcher.find();
	}
	
	private String getSearchFileRootPath(List<String> args) {
		return args.get(0);
	}
	
	private String getSearchTargetFileName(List<String> args) {
		return args.get(1);
	}
}
