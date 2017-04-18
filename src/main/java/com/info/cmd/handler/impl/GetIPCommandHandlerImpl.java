package com.info.cmd.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;  

@Component("getIPCommandHandler")
public class GetIPCommandHandlerImpl extends AbstractCommandHandlerImpl {
	
	private final static Logger logger = Logger.getLogger(GetIPCommandHandlerImpl.class);
	
	
	//检查当前运行服务器IP地址
	@Override
	protected String exec(String cmd, List<String> args) {
		String ip = null;
		try {
			ip = getHostIp();
		} catch (Exception e ) {
			logger.error("error run check ip command exec");
			return "查询ip地址失败";
		}
		return "当前ip地址为:" + ip;
	}

	@Override
	protected boolean isMatchHandlerRule(String cmd) {
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
	
    private String getHostIp() throws UnknownHostException{
    	InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();  
    }  
	
}
