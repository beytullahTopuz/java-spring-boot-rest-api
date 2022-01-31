package com.t4zb.cvr;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ApiError {
	private int status;
	private String message;
	private String path;
	private long timestemp = new Date().getTime();

	private Map<String, String> validationError;
	
	public ApiError(int status, String message, String path) {
		this.status = status;
		this.message = message;
		this.path = path;
	}
	
}
