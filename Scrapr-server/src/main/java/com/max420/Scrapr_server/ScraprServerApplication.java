package com.max420.Scrapr_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ScraprServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScraprServerApplication.class, args);
	}

}
