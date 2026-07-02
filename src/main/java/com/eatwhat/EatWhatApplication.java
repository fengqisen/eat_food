package com.eatwhat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eatwhat.mapper")
public class EatWhatApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatWhatApplication.class, args);
    }
}