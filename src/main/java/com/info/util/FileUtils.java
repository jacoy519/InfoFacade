package com.info.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
	
	public static List<String> findFile(String fileNameRegEx, String dirPath) {

		List<String> result = new ArrayList<String>();
		File file = new File(dirPath);
		if(!file.isDirectory()) {
			return result;
		}
		Pattern pattern = Pattern.compile(fileNameRegEx);
		String[] currentFileList = file.list();
		if(currentFileList == null) {
			return result;
		}
		for(String currentFileName : currentFileList) {		
			String currentFilePath = dirPath + "/" + currentFileName;
			Matcher matcher = pattern.matcher(currentFileName);
			if(matcher.find()) {
				result.add(currentFilePath);
			}
			result.addAll(findFile(fileNameRegEx,currentFilePath));
		}
		return result;
	}
	
	public static void main(String[] args) {
		try {
			List<String> filePath = findFile("baidu", "D:/");
			for(String file: filePath) {
				System.out.println(file);
			}
			System.out.println("finish");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
