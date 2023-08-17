package com.example.cookinn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CookinnApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookinnApplication.class, args);
    }

}
