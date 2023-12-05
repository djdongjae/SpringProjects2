package com.groom.cookiehousedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CookieHouseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookieHouseDemoApplication.class, args);
    }

}
