package com.ipasoft.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipasoft.demo.services.IIpaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ipa")
public class IpaRestController {

	@Autowired
	@Qualifier("ipaService")
	private IIpaService ipaService;
	
	@Autowired
	private IIpaService macaService;
	
	@GetMapping()
	public String get(HttpServletRequest request) {
		long startTime = (long) request.getAttribute("startTime");
		//long requestDuration = (long) request.getAttribute("requestDuration");
		return "rest get startTime" + startTime + ipaService.anOperation() + " requestDuration" ;
	}
	
	@GetMapping("/maca")
	public String getMaca() {
		return "rest get "+ macaService.anOperation();
	}
	
}