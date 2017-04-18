package com.info.cmd.handler.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Component;

@Component("getDiscSpaceCommandHandler")
public class GetDiscSpaceCommandHandlerImpl extends AbstractCommandHandlerImpl{



	@Override
	protected boolean isMatchHandlerRule(String cmd) {
		String regEx="获取磁盘容量";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(cmd);
		return matcher.find();
	}
	
	@Override
	protected String exec(String cmd, List<String> args) throws Exception{
		
		return getAllDiscSpaceMessage();
	}
	
	
	@Override
	protected String getExecFailMessage() {
		// TODO Auto-generated method stub
		return "获取磁盘信息失败";
	}
	
	private String getAllDiscSpaceMessage() {
		StringBuffer spaceMessage = new StringBuffer();
		File[] fs = File.listRoots();
		for(File disc : fs) {
			if(disc.exists()) {
				String message = getDiscSpaceMessage(disc);
				spaceMessage.append(message).append('\n');
			}
		}
		return spaceMessage.toString();
	}
	
	private String getDiscSpaceMessage(File disc) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String discName = fsv.getSystemDisplayName(disc);
		String totalSpace = FormetFileSize(disc.getTotalSpace());
		String remainSpace = FormetFileSize(disc.getFreeSpace());
		String singleDiscSpaceMessage = discName + " 总大小："+ totalSpace + " 剩余大小：" + remainSpace;
		return singleDiscSpaceMessage;
	}
	
	private String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}


	
}
