package com.ipasoft.demo.controllers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDataDTO { 
	private String code;
	private String detail;
	private String technicalInfo;
}