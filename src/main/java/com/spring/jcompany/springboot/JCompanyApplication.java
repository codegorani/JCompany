package com.spring.jcompany.springboot;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaAuditing
@EnableBatchProcessing
@SpringBootApplication
public class JCompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JCompanyApplication.class, args);
    }
}
