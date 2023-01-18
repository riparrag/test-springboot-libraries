package com.ipasoft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	/*
	@Autowired
	private IClientDAO clientDAO;

	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	clientDAO.save(new Client("John"));
	    	clientDAO.save(new Client("Rambo"));
	      };
	   }
	}*/
}
