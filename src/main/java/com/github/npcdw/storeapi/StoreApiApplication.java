package com.github.npcdw.storeapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.github.npcdw.storeapi.mapper")
public class StoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApiApplication.class, args);
    }

}
