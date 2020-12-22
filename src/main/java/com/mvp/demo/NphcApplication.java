package com.mvp.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class NphcApplication {
	 
	Logger LOG = LoggerFactory.getLogger(NphcApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(NphcApplication.class, args);
	}

}
