package com.ipasoft.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/v1")
public class IpasoftController {

	@GetMapping
	public String getAlgo() {

		return "algo";
	}
	
	@RequestMapping(value = "/algo-mas", method = RequestMethod.GET)
	public String getAlgoMas() {
		return "Algo Mas";
	}
	
	@GetMapping({"/","/algo-con-alias","/home","/index"})
	public String getAlgoConAlias(Model model) {
		model.addAttribute("name", "model name algo con alias");
		return "index.html";
	}
}