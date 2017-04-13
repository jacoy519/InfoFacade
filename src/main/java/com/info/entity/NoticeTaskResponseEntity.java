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
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NoticeTaskResponseEntity)) {
			return false;
		}
		NoticeTaskResponseEntity input = (NoticeTaskResponseEntity)obj;
		
		return this.status.equals(input.getStatus()) &&
				this.code.equals(input.getCode());
	}
	
}
