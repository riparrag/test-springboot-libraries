package com.ipasoft.demo.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("RestApiInterceptor")
public class RestApiInterceptor implements HandlerInterceptor {
	private final static Logger LOGGER = LoggerFactory.getLogger(RestApiInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("RO PRE HANDLE");
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		//Thread.sleep(2000);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		LOGGER.info("RO post HANDLE");
		long endTime = System.currentTimeMillis();
		long startTime = (long)request.getAttribute("startTime");
		long requestDuration = endTime - startTime;
		LOGGER.info("Request Total duration: "+ requestDuration);
		request.setAttribute("requestDuration", requestDuration);
	}
}
