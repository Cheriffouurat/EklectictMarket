package com.example.eklecticproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication

public class EklecticProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EklecticProjectApplication.class, args);
    }

}
