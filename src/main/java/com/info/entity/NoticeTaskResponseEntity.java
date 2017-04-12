package com.info.entity;

public class NoticeTaskResponseEntity {
	
	private String code;
	
	private String status;
	
	public NoticeTaskResponseEntity(String code, String status) {
		this.code = code;
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getCode() {
		return this.code;
	}
	
}
