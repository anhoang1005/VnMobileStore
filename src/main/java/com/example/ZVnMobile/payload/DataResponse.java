package com.example.ZVnMobile.payload;

public class DataResponse {
	private int status = 200;
	private String message;
	private Object data;
	private boolean success;
	public DataResponse() {
		super();
	}
	public DataResponse(int status, String message, Object data, boolean success) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.success = success;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
