package com.banking.automation.irumole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IrumoleApplication {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		SpringApplication.run(IrumoleApplication.class, args);
	}

}

