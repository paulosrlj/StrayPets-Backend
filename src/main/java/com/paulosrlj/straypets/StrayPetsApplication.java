package com.paulosrlj.straypets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StrayPetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrayPetsApplication.class, args);
    }

}
