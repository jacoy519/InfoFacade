package com.info.util;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

import java.util.Set;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtils {
	
	
	
	public static List<String> getFileList(String filePath) throws Exception {
		List<String> fileList = new ArrayList<String>();
		File file = new File(filePath);
		if(!file.isDirectory()) {
			throw new Exception(filePath + "不是文件夹");
		}
		String[] currentFileList = file.list();
		for(String currentfilePath : currentFileList) {
			fileList.add(currentfilePath);
		}
		return fileList;
	}
	
	public static Set<String> searchFile(String fileNameRegEx, final String searchRootPath) throws InterruptedException, ExecutionException {
		final Set<String> result = new ConcurrentSkipListSet<String>();
		final Pattern fileNamePattern = Pattern.compile(fileNameRegEx);
		File file = new File(searchRootPath);
		ExecutorService fileExecutor = Executors.newFixedThreadPool(5);
		if(!file.isDirectory()) {
			return result;
		}
		String[] childFileNames = file.list();
		if(childFileNames == null) {
			return result;
		}
		final CountDownLatch lock = new CountDownLatch(childFileNames.length);
		for(final String childFileName : childFileNames) {
			fileExecutor.execute(new Runnable(){
				public void run() {
					final String childFilePath = searchRootPath + '/' + childFileName;
					Matcher matcher = fileNamePattern.matcher(childFileName);
					if(matcher.find()) {
						result.add(childFilePath);
					}
					try {
						searchFile(fileNamePattern, childFilePath, result);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock.countDown();
				}});
		}
		lock.await();
		fileExecutor.shutdown();
		return result;
	}
	
	public static void searchFile(final Pattern fileNamePattern, final String searchRootPath,final Set<String> result) throws InterruptedException {
		File file = new File(searchRootPath);
		if(!file.isDirectory()) {
			return;
		}
		String[] childFileNames = file.list();
		if(childFileNames == null) {
			return;
		}
		ExecutorService fileExecutor = Executors.newFixedThreadPool(5);
		final CountDownLatch lock = new CountDownLatch(childFileNames.length);
		for(final String childFileName : childFileNames) {
			fileExecutor.execute(new Runnable(){
				public void run() {
					final String childFilePath = searchRootPath + '/' + childFileName;
					Matcher matcher = fileNamePattern.matcher(childFileName);
					if(matcher.find()) {
						result.add(childFilePath);
					}
					try {
						searchFile(fileNamePattern, childFilePath, result);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock.countDown();
				}});
		}
		lock.await();
		fileExecutor.shutdown();
	}
	
	
	public static void main(String[] args) {
		try {
			Set<String> filePath = searchFile("baidu", "D:/");
			for(String file: filePath) {
				System.out.println(file);
			}
			System.out.println("finish");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
