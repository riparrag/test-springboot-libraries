package com.ipasoft.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
@Qualifier("ipaService")
public class IpaService implements IIpaService {

	@PostConstruct
	public void initialize() {
		System.out.println("post construct");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("pre destroy");
	}
	
	public String anOperation() {
		return "ipa operation";
	}	
}