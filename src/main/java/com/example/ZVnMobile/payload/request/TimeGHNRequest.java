package com.example.ZVnMobile.payload.request;

public class TimeGHNRequest {
	private String from_district_id;
	private String from_ward_code;
	private String to_district_id;
	private String to_ward_code;
	private String service_id;
	public String getFrom_district_id() {
		return from_district_id;
	}
	public void setFrom_district_id(String from_district_id) {
		this.from_district_id = from_district_id;
	}
	public String getFrom_ward_code() {
		return from_ward_code;
	}
	public void setFrom_ward_code(String from_ward_code) {
		this.from_ward_code = from_ward_code;
	}
	public String getTo_district_id() {
		return to_district_id;
	}
	public void setTo_district_id(String to_district_id) {
		this.to_district_id = to_district_id;
	}
	public String getTo_ward_code() {
		return to_ward_code;
	}
	public void setTo_ward_code(String to_ward_code) {
		this.to_ward_code = to_ward_code;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	
}
