package com.bgatebco.api.utils;

public enum ErrorCode {

	FAIL(0, "fail"),
	SUCCESS(1, "success");
	
	private int code;
	private String message;
	
	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int getCode() {
		return this.code;
	}
	
}
