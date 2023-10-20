package com.example.springbootvuetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootVueTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVueTestApplication.class, args);
    }

}
