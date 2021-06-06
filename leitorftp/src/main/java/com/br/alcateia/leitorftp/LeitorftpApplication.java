package com.br.alcateia.leitorftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeitorftpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeitorftpApplication.class, args);
	}

}
