package com.fb.locations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Entry point to application
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.fb.locations.*"})
public class LocationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationsApplication.class, args);
    }
}
