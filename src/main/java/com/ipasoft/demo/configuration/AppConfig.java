package com.ipasoft.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ipasoft.demo.services.IIpaService;
import com.ipasoft.demo.services.IpaBean;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("RestApiInterceptor")
	private HandlerInterceptor restApiInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		
		registry.addInterceptor( restApiInterceptor );
	}
	
	@Bean("ipaBean")
	public IIpaService registerIpaBean() {
		return new IpaBean();
	}
	
}