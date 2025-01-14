package com.movie.lucky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RocommedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocommedServiceApplication.class, args);
	}

}
