package com.banking.automation.irumole.dto;

import lombok.Data;

@Data
public class Response {
	
	private String responseMessage = "invalid parameters";
	private String responseStatus = "failed";
	private Object response;
	
	public Response() {
		
	}
	
	public Response(String responseMessage, String responseStatus, Object response) {
		super();
		this.responseMessage = responseMessage;
		this.responseStatus = responseStatus;
		this.response = response;
	}

}
