package com.irumole.ng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IrumoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrumoleApplication.class, args);
    }
}

