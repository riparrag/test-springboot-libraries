package com.ipasoft.demo.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MacaService implements IIpaService {

	public String anOperation() {
		return "maca operation";
	}
	
}