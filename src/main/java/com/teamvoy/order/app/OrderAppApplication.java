package com.teamvoy.order.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OrderAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderAppApplication.class, args);
    }

}
