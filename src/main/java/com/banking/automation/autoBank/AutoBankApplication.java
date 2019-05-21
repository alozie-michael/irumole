package com.banking.automation.autoBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AutoBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoBankApplication.class, args);
	}

}

