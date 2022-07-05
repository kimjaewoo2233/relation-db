package com.example.relationdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RelationDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationDbApplication.class, args);
    }

}
