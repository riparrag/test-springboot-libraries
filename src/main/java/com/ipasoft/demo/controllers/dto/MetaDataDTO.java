package com.ipasoft.demo.controllers.dto;

import lombok.Data;

@Data
public class MetaDataDTO { 
	private String method;
	private String operation;
	
	public MetaDataDTO() {}
	public MetaDataDTO(String method, String operation) {
		this.method = method;
		this.operation = operation;
	}
}