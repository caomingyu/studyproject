package com.example.springsecuritydemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.springsecuritydemo.mapper"})
@Slf4j
public class SpringsecuritydemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritydemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("学习验证Spring Security");
    }
}
