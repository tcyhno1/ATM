package com.yuhao.atmboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.yuhao.atmboot.mapper")
public class AtmBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmBootApplication.class, args);
    }
}
