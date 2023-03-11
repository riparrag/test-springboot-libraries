package com.ipasoft.demo.utils;

import com.ipasoft.demo.controllers.dto.MetaDataDTO;

import jakarta.servlet.http.HttpServletRequest;

public class ControllersUtils {

	public static MetaDataDTO crearMetaData(HttpServletRequest httpServletRequest) {
		return new MetaDataDTO(httpServletRequest.getMethod(), httpServletRequest.getRequestURI().toString());
	}
}