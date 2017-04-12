package com.info.handler;

public interface MessageHandler {
	
	String receiveMessage();
	
	void sendMessage(String message) throws Exception;
}
