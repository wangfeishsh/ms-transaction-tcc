package com.bao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MsAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAccountApplication.class, args);
	}
}
