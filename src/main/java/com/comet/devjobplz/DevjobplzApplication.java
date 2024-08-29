package com.comet.devjobplz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevjobplzApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevjobplzApplication.class, args);
    }

}
