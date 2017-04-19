package com.info.cmd.handler.impl;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.info.util.FileUtils;

@Component("findFileCommandHandler")
public class FindFileCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	@Resource
	private ThreadPoolTaskExecutor findFileTaskExecutor; 

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
		Set<String> searchFilePaths = FileUtils.searchFile(targetFile, fileRootPath);
		return searchFilePathCollectionToString(searchFilePaths);
	}
	
	@Override
	protected String getExecFailMessage() {
		// TODO Auto-generated method stub
		return "查找文件失败";
	}
	
	private String getSearchFileRootPath(List<String> args) {
		return args.get(0);
	}
	
	private  Set<String> searchFiles(String fileNameRegEx, String searchRootPath) throws InterruptedException, ExecutionException {
		final Set<String> result = new ConcurrentSkipListSet<String>();
		final Pattern fileNamePattern = Pattern.compile(fileNameRegEx);
		File file = new File(searchRootPath);
		if(!file.isDirectory()) {
			return result;
		}
		String[] childFileNames = file.list();
		if(childFileNames == null) {
			return result;
		}
		final CountDownLatch lock = new CountDownLatch(childFileNames.length);
		for(final String childFileName : childFileNames) {
			final String childFilePath = searchRootPath + '/' + childFileName;
			Matcher matcher = fileNamePattern.matcher(childFileName);
			if(matcher.find()) {
				result.add(childFilePath);
			}
			findFileTaskExecutor.execute(new Runnable(){

				public void run() {
					searchFile(fileNamePattern, childFilePath, result);
					lock.countDown();
				}});
			
		}
		lock.await();
		return result;
	}
	
	private void searchFile(final Pattern fileNamePattern, final String searchRootPath,final Set<String> result) {
		File file = new File(searchRootPath);
		if(!file.isDirectory()) {
			return;
		}
		String[] childFileNames = file.list();
		if(childFileNames == null) {
			return;
		}
		for(final String childFileName : childFileNames) {
			final String childFilePath = searchRootPath + '/' + childFileName;
			Matcher matcher = fileNamePattern.matcher(childFileName);
			if(matcher.find()) {
				result.add(childFilePath);
			}
			searchFile(fileNamePattern, childFilePath, result);
		}
	}
	
	private String getSearchTargetFileName(List<String> args) {
		return args.get(1);
	}
	
	private String searchFilePathCollectionToString(Collection<String> searchFileList) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("搜索结果:\n");
		for(String searchFilePath : searchFileList) {
			strBuilder.append(searchFilePath);
			strBuilder.append('\n');
		}
		return strBuilder.toString();
	}


}
