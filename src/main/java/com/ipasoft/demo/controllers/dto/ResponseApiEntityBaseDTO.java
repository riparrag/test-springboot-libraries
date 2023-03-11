package com.ipasoft.demo.controllers.dto;

import java.util.ArrayList;
import java.util.List;

import com.ipasoft.demo.utils.ControllersUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class ResponseApiEntityBaseDTO<T> {
	private MetaDataDTO meta;
	private List<ErrorDataDTO> errors;
	private T data;
	
	public ResponseApiEntityBaseDTO() {}
	public ResponseApiEntityBaseDTO(T data) {
		this.data = data;
	}
		
	public ResponseApiEntityBaseDTO(HttpServletRequest httpServletRequest) {
		this.meta = ControllersUtils.crearMetaData( httpServletRequest );
	}
	
	public void addError(String code, String detail, String technicalInfo) {
		this.getErrorsInitialited().add( new ErrorDataDTO());
	}
	
	public List<ErrorDataDTO> getErrorsInitialited() {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		return this.errors;
	}
	public void setData(T data) {
		this.data = data;
	}
}