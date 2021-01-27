package com.spring.jcompany.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JCompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JCompanyApplication.class, args);
    }
}
