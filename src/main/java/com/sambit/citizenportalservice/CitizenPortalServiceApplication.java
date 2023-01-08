package com.sambit.citizenportalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitizenPortalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitizenPortalServiceApplication.class, args);
    }

}
