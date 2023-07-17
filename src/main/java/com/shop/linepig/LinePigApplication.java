package com.shop.linepig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LinePigApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinePigApplication.class, args);
    }

}
