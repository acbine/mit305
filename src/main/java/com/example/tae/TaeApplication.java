package com.example.tae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaeApplication.class, args);
    }

}
