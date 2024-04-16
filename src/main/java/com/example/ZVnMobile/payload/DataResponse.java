package com.example.ZVnMobile.payload;

public class DataResponse {
	private int status = 200;
	private String message;
	private String errorCode;
	private Object data;
	private Integer pageData;
	private boolean success;

	public DataResponse() {
		super();
	}
	public DataResponse(int status, String message, String errorCode, Object data, Integer pageData, boolean success) {
		super();
		this.status = status;
		this.message = message;
		this.errorCode = errorCode;
		this.data = data;
		this.pageData = pageData;
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
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getPageData() {
		return pageData;
	}
	public void setPageData(Integer pageData) {
		this.pageData = pageData;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
