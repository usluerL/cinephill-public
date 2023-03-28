package com.byusluer.movierest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class MovieRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRestApplication.class, args);
    }

}
