package com.max420.Scrapr_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
		"com.max420.Scrapr_server.models",
		"com.max420.Scrapr_server.config"
})
@EntityScan(basePackages = {"com.max420.Scrapr_server.models"})
@SpringBootApplication
public class ScraprServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScraprServerApplication.class, args);
	}

}
