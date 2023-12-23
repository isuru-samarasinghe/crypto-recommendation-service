package com.xm.cryptoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Application class is the entry point of the Spring Boot application.
 * It includes the main method which starts the application.
 * 
 * @EnableScheduling annotation is used to enable scheduling tasks.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    /**
     * The main method which serves as the entry point for the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}