package com.project.mentorbabaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.mentorbabaa")
public class MedicureApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicureApplication.class, args);
    }
}
